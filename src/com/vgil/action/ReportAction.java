package com.vgil.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.core.action.BaseAction;
import com.core.bo.ApplicationSettingsBo;
import com.core.report.GridConfig;
import com.vgil.bo.impl.ChallengeBankBOImpl;
import com.vgil.bo.impl.EmployeeBOImpl;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;
import com.vgil.model.TeamMembers;
import com.vgil.model.ChallengePoints;
import com.vgil.model.ChallengeTeam;
import com.vgil.model.CompanyMaster;

import static com.vgil.model.ApplicationConstants.*;

public class ReportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private long id;
	private Employee employee;
	private ApplicationSettingsBo applicationSettingsBo;
	private String company = null;
	private List<String> UserchallengeList = new ArrayList<String>();
	//@Autowired
	private ChallengeBankBOImpl bankBO;
	private ChallengeMaster challenge;
	
	public ChallengeMaster getChallenge() {
		return challenge;
	}

	public void setChallenge(ChallengeMaster challenge) {
		this.challenge = challenge;
	}
	//@Autowired
	private EmployeeBOImpl employeeBO;
	
	
	
	public String getPlants(){
		List<String> objPlantGroup = new ArrayList<String>();
		
		gridConfig = new GridConfig();
		
	
		System.out.println("The data is "+getSession().get("UserAccesiblePlants"));
		@SuppressWarnings("unchecked")
		HashMap<String,List<String>> companyPLant = (HashMap<String,List<String>>)getSession().get("UserAccesiblePlants");
		System.out.println("HashMap recieved.");
		objPlantGroup = companyPLant.get(company);
		gridConfig.setGridModel(objPlantGroup);
		 
		return SUCCESS;
	}
	
	public String getChallengeBank(){
		try {
			Employee employee = ((Employee)(getSession().get("user")));
			
			int rows_count = bankBO.getChallengeBankCount(employee);
			
			gridConfig = new GridConfig();
			gridConfig.configureGrid(request, rows_count);
			
			gridConfig.setGridModel(bankBO.getChallengeBankList(employee));

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	public List<ChallengeMaster> getChallengeList(){
		
		List<ChallengeMaster> challenges;
		
		try {
			Employee employee = ((Employee)(getSession().get("user")));
			challenges = bankBO.getChallengeBankList(employee);
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return challenges;
	}
	
	public String getChallengeListTest(){
		try {
			//Employee employee = ((Employee)(getSession().get("user")));
			int rows_count = bankBO.getChallengeBankCount(employee);
			gridConfig = new GridConfig();
			gridConfig.configureGrid(request, rows_count);
			gridConfig.setGridModel(bankBO.getChallengeBankList(employee));

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String personalPointsTracker(){
		
		try {
			
			    Employee employee = ((Employee)(getSession().get("user")));
				gridConfig = new GridConfig();
				List<ChallengePoints> ChallengePointsForCalculation = bankBO.getChallengePointsTracker(employee);
   			    gridConfig.setGridModel(ChallengePointsForCalculation);
   			    
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String pursueChallenge()
	{
		ChallengeTeam team = new ChallengeTeam();
		TeamMembers teamMember = new TeamMembers();
		String challengeId = challenge.getChallenge_id();
		Employee loggedInEmployee = (Employee)getSession().get("user");
		boolean result = false;
		boolean resultMember = false;		
		ChallengeMaster challengeMaster;
		
		
		
		try {

			challengeMaster = bankBO.loadChallenge(challengeId);
			int teamSerial = bankBO.getTeamLatestSerialno();
			int memberSerial = bankBO.getMemberLatestSerialno();
			
			team.setSerial_no(teamSerial+1);
			team.setTeam_id(TEAMCONSTANT+(teamSerial+1));
			team.setUser_id(loggedInEmployee.getUser_id());
			team.setChallenge_id(challengeMaster.getChallenge_id());
			team.setChallenge_status(CHALLENGEPURSUED);
			team.setCreated_date(new Timestamp(System.currentTimeMillis()));
			team.setCreated_by(loggedInEmployee.getUser_id());
			team.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
			team.setLast_updated_by(loggedInEmployee.getUser_id());
			result = bankBO.saveOrUpdateChallengeTeam(team);
			
			if(result)
			{
				teamMember.setSerial_no(memberSerial+1);
				teamMember.setTeam_id(team.getTeam_id());
				teamMember.setMember_type(CHALLENGEOWNER);
				teamMember.setMember_id(loggedInEmployee.getUser_id());
				teamMember.setCreated_date(new Timestamp(System.currentTimeMillis()));
				teamMember.setCreated_by(loggedInEmployee.getUser_id());
				teamMember.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				teamMember.setLast_updated_by(loggedInEmployee.getUser_id());
				resultMember = bankBO.saveOrUpdateChallengeMembers(teamMember);
			}
			else
			{
				addActionError("There was a problem in Saving the Team Information. Please contact the Administrator.");
			}
			
			
			if(resultMember)
			{
				challengeMaster.setChallenge_status(CHALLENGEPURSUED);
				bankBO.saveOrUpdateChallenge(challengeMaster);	
			}
			else
			{
				addActionError("There was a problem in changing the Challenge Status. Please contact the Administrator.");
			}

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
					
		
		return SUCCESS ;
	}
	
	public String getChallengeIdeas(){
		/*try {
			List<IdeaMaster> ideaBanks =  new ArrayList<IdeaMaster>();
			Employee employee = ((Employee)(getSession().get("user")));
			List challengeNos = new ArrayList();
			int rows_count = bankBO.getChallengeBankCount(employee);
			List<ChallengeMaster> challenges = bankBO.getChallengeBankList(employee,0, rows_count);
			for (Iterator iterator = challenges.iterator(); iterator.hasNext();) {
				ChallengeMaster challenge = (ChallengeMaster) iterator.next();
				if(challenge.getChallenge_status() != null && challenge.getChallenge_status().contains(""))
				challengeNos.add(challenge.getChallenge_status());
				//ideaBanks.addAll(bankBO.getChallengeIdeas(challenge.getId()));
			}
			List<IdeaMaster> ideas = bankBO.getChallengeIdeas(challengeNos);
			//TO BE PLANNED.
				long challengeId = Long.parseLong(bank.getChallenge_id());
				for(ChallengeMaster chall : challenges){
					if(Integer.parseInt(chall.getChallenge_id()) == challengeId){
						bank.setChallengeType(chall.getChallenge_type());
					}
				}
			}
			gridConfig = new GridConfig();
			int i = 0;
			Map ideaNoMap = new HashMap();
			for (Iterator iterator = ideas.iterator(); iterator.hasNext();) {
				i++;
				IdeaMaster ideaBank = (IdeaMaster) iterator.next();
				ideaNoMap.put(i, ideaBank.getIdea_id());
				ideaBank.setIdea_id(Integer.toString(i));
			}
			getSession().put("ideaNoMap", ideaNoMap);
			gridConfig.setGridModel(ideas);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}*/
		return SUCCESS;
	}
	public String getOpenChallengeIdeas(){
		return company;
		/*try {
			List<IdeaMaster> ideaBanks =  new ArrayList<IdeaMaster>();
			Employee employee = ((Employee)(getSession().get("user")));
			List challengeNos = new ArrayList();
			int rows_count = bankBO.getChallengeBankCount(employee);
			List<ChallengeMaster> challenges = bankBO.getChallengeBankList(employee,0, rows_count);
			for (Iterator iterator = challenges.iterator(); iterator.hasNext();) {
				ChallengeMaster challenge = (ChallengeMaster) iterator.next();
				if(challenge.getChallenge_status() != null && challenge.getChallenge_status().contains(""))
				challengeNos.add(challenge.getChallenge_id());
				//ideaBanks.addAll(bankBO.getChallengeIdeas(challenge.getId()));
			}
			List<IdeaMaster> ideas = bankBO.getChallengeIdeas(challengeNos);
			for (Iterator iterator = ideas.iterator(); iterator.hasNext();) {
				IdeaMaster ideaBank = (IdeaMaster) iterator.next();
				if(ideaBank.getIdea_status().contains("Closed")) {
					iterator.remove();
				}
			}
			gridConfig = new GridConfig();
			int i = 0;
			Map ideaNoMap = new HashMap();
			for (Iterator iterator = ideas.iterator(); iterator.hasNext();) {
				i++;
				IdeaMaster ideaBank = (IdeaMaster) iterator.next();
				ideaNoMap.put(i, ideaBank.getIdea_id());
				ideaBank.setIdea_id(Integer.toString(i));
			}
			getSession().put("openIdeaNoMap", ideaNoMap);
			gridConfig.setGridModel(ideas);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;*/
	}
	public String getChallengePoints(){
		/*try {
			Employee employee = ((Employee)(getSession().get("user")));
			gridConfig = new GridConfig();
			List challenges = bankBO.getChallengePoints(employee);
			int i = 0;
			Map challengeNoMap = new HashMap();
			for (Iterator iterator = challenges.iterator(); iterator.hasNext();) {
				i++;
				ChallengePoints challenge = (ChallengePoints) iterator.next();
				challengeNoMap.put(i, challenge.getChallenge_id());
				challenge.setChallenge_id(Integer.toString(i));
			}
			getSession().put("trackChallengeNoMap", challengeNoMap);
			gridConfig.setGridModel(challenges);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}*/
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getCompanyChallenges(){
		
		List<ChallengeMaster> challengeList;
		List<CompanyMaster> companyMaster;
		List<PlantMaster> plantMaster;
		
		try {
			Employee employee = ((Employee)(getSession().get("user")));
			if(!employee.getUserRole().equals(ADMIN_USER))
			{
				
			    challengeList = bankBO.getCompanyChallenges(employee.getCompanyID());
				
			}
			else
			{
				
				challengeList = bankBO.getCompanyChallenges(null);
				
			}
			
			companyMaster = (List<CompanyMaster>)session.get("MasterCompanyData");
			plantMaster = (List<PlantMaster>)session.get("MasterPlantData");
			for(int iter=0;iter<challengeList.size();iter++)
			{
				
				for(int i=0;i<companyMaster.size();i++)
				{
					if(companyMaster.get(i).getCompany_id().equalsIgnoreCase(challengeList.get(iter).getCompany_id()))
					{
						challengeList.get(iter).setCompany_name(companyMaster.get(i).getCompany_name());
						challengeList.get(iter).setSerial_no(iter+1);
					}
				}
				
				for(int i=0;i<plantMaster.size();i++)
				{
					if(plantMaster.get(i).getPlant_id().equalsIgnoreCase(challengeList.get(iter).getPlant_id()))
					{
						challengeList.get(iter).setPlant_name(plantMaster.get(i).getPlant_name());
					}
				}
				
			}
			
			
			gridConfig = new GridConfig();
			gridConfig.setGridModel(challengeList);

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getOpenChallenges(){
		try {
			
			Employee employee = ((Employee)(getSession().get("user")));
			List<ChallengeMaster> challenges = bankBO.getChallengeBankListForCompany(employee);
			gridConfig = new GridConfig();
			for(int serial=0;serial<challenges.size();serial++)
			{
				challenges.get(serial).setSerial_no(serial+1);
				challenges.get(serial).setCompany_name(getcompanyName(challenges.get(serial).getCompany_id()));
				challenges.get(serial).setPlant_name(getPlantName(challenges.get(serial).getPlant_id()));
			}
			gridConfig.setGridModel(challenges);

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	private String getcompanyName(String id)
	{
		String name =null;
		@SuppressWarnings("unchecked")
		List<CompanyMaster> company = (ArrayList<CompanyMaster>) getSession().get("MasterCompanyData");
		for(int i=0;i<company.size();i++)
		{
			if(id.equals(company.get(i).getCompany_id()))
			{
				name = company.get(i).getCompany_name();
			}

		}
		
		return name;

	}
	private String getPlantName(String id)
	{
		String name = null;
		@SuppressWarnings("unchecked")
		List<PlantMaster> plant = (ArrayList<PlantMaster>) getSession().get("MasterPlantData");
		for(int i=0;i<plant.size();i++)
		{
			if(id.equals(plant.get(i).getPlant_id()))
			{
				name = plant.get(i).getPlant_name();
				
			}

		}
		
		return name;
	}
	public String searchChallengeBank(){
		try {
			int rows_count = bankBO.countByCriteriaForChallengeBank(searchField, searchOper, searchString);
			
			gridConfig = new GridConfig();
			gridConfig.configureGrid(request, rows_count);
			
			gridConfig.setGridModel(bankBO.findByCriteriaForChallengeBank(searchField, searchOper, searchString, 0, rows_count));

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	public String getApplicaitonSettings(){
		try {
			int rows_count = bankBO.getOpenChallengesCount();
			
			gridConfig = new GridConfig();
			gridConfig.setGridModel(bankBO.getOpenChallengeList(0, rows_count));

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String getTeamMembers(){
		try {
			Employee employee = ((Employee)(getSession().get("user")));
			int rows_count = employeeBO.getSameCompanyPlantEmployeeCount(employee.getCompanyID(), employee.getPlantID());
			
			gridConfig = new GridConfig();
			gridConfig.configureGrid(request, rows_count);
			
			gridConfig.setGridModel(employeeBO.getSameCompanyPlantEmployeeList(employee.getCompanyID(), 
					employee.getPlantID(),0, rows_count));

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	private static Comparator<Employee> bySerial_no()
	{
	    return new Comparator<Employee>()
	    {
	        @Override
	        public int compare(Employee o1, Employee o2)
	        {
	            return o1.getSerial_no() - o2.getSerial_no();
	        }
	    };        
	}
	
	public String getUserList() {
		try {
			Employee employee = ((Employee) (getSession().get("user")));
			gridConfig = new GridConfig();
			List<Employee> employees = employeeBO.getEmployeeData(employee);
			Collections.sort(employees,bySerial_no());
			gridConfig.setGridModel(employees);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getUserListTest() {
		try {
			//Employee employee = ((Employee) (getSession().get("user")));
			//employee = this.employee;
			gridConfig = new GridConfig();
			gridConfig.setGridModel(employeeBO.getEmployeeData(employee));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String getChallengeTrackingPage(){
		int iter;
		List<ApplicationSettings> challengeType = bankBO.getChallengeDetails(CHALLENGETYPELIST);
		List<ApplicationSettings> challengeCategory = bankBO.getChallengeDetails(CATEGORYLIST);
		List<ApplicationSettings> challengePriority = bankBO.getChallengeDetails(PRIORITY);
		List<ApplicationSettings> challengeStatus = bankBO.getChallengeDetails(CHALLENGESTATUS);
		
		List<String> type = new ArrayList<String>();
		List<String> category = new ArrayList<String>();
		List<String> priority = new ArrayList<String>();
		List<String> status = new ArrayList<String>();
		
		for(iter=0;iter<challengeType.size();iter++)
		{
			type.add(challengeType.get(iter).getValue());
		}
		getSession().put("ChallengeTypeList",type);
		
		
		for(iter=0;iter<challengeCategory.size();iter++)
		{
			category.add(challengeCategory.get(iter).getValue());
		}
		getSession().put("ChallengeCategoryList",category);
		
	
		for(iter=0;iter<challengePriority.size();iter++)
		{
			priority.add(challengePriority.get(iter).getValue());
		}
		getSession().put("ChallengePriorityList",priority);
		
		
		
		for(iter=0;iter<challengeStatus.size();iter++)
		{
			status.add(challengeStatus.get(iter).getValue());
		}
		getSession().put("ChallengeStatusList",status);
		
		return SUCCESS;
	}
	public String getChallengePickPage(){
		
		List<ChallengeMaster> list = getChallengeList();
		
		if(list!=null)
		{
			
		for(int iter = 0;iter<list.size();iter++)
		{
			UserchallengeList.add(list.get(iter).getChallenge_id());	
		}
		
		return SUCCESS;
		}
		
		else
		{
			addActionError("An error occurred while getting the Challenges List. Please contact your administrator for Help.");
			return ERROR;
		}

		
	}
	
	@Override
	public void validate() {

		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ApplicationSettingsBo getApplicationSettingsBo() {
		return applicationSettingsBo;
	}
	public void setApplicationSettingsBo(ApplicationSettingsBo applicationSettingsBo) {
		this.applicationSettingsBo = applicationSettingsBo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public ChallengeBankBOImpl getBankBO() {
		return bankBO;
	}
	public void setBankBO(ChallengeBankBOImpl bankBO) {
		this.bankBO = bankBO;
	}
	public EmployeeBOImpl getEmployeeBO() {
		return employeeBO;
	}
	public void setEmployeeBO(EmployeeBOImpl employeeBO) {
		this.employeeBO = employeeBO;
	}
	public List<String> getUserchallengeList() {
		return UserchallengeList;
	}
	public void setUserchallengeList(List<String> userchallengeList) {
		UserchallengeList = userchallengeList;
	}
	
	

	

}