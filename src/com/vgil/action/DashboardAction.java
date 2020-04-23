package com.vgil.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.core.action.BaseAction;
import com.vgil.bo.ChallengeBankBO;
import com.vgil.bo.EmployeeBO;
import com.vgil.bo.ManPowerBO;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.ChallengeMatrixVO;
import com.vgil.model.Chart;
import com.vgil.model.ChartData;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.Manpower;

import com.vgil.model.PersonalTrackerVO;


import static com.vgil.model.ApplicationConstants.*;




public class DashboardAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	protected String m_errorMessage = "";
	ChallengeMaster challenge;

	//@Autowired
	private ChallengeBankBO challengeBankBo;
	//@Autowired
	private ManPowerBO manPowerBo;
	//@Autowired
	private EmployeeBO employeeBo;
	private Chart chart = new Chart();

	ChallengeMatrixVO chaMatrixVO = new ChallengeMatrixVO();
	PersonalTrackerVO trackerVO = new PersonalTrackerVO();
	public PersonalTrackerVO getTrackerVO() {
		return trackerVO;
	}
	public void setTrackerVO(PersonalTrackerVO trackerVO) {
		this.trackerVO = trackerVO;
	}

	Manpower Manpower = new Manpower();

	Manpower manpowerEmp = new Manpower();

	Employee employee= new Employee();

	//JFreeChart chart;

	String pieChartName;

	String message;

	public String getMessage() {
		return message;
	}
	public String getPieChartName() {
		return pieChartName;
	}
	public void setPieChartName(String pieChartName) {
		this.pieChartName = pieChartName;
	}
	public String getLoadPieChart() {
		return loadPieChart;
	}
	public void setLoadPieChart(String loadPieChart) {
		this.loadPieChart = loadPieChart;
	}

	String loadPieChart;

	String selectedCompanyName ="";

	String userRole;

	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getSelectedCompanyName() {
		return selectedCompanyName;
	}
	public void setSelectedCompanyName(String selectedCompanyName) {
		this.selectedCompanyName = selectedCompanyName;
	}
	public String getDefaultCompanyName() {
		return getSessionEmployee().getCompanyID();
	}

	private List<String> compNames;

	public List<String> getCompNames() {
		return compNames;
	}
	public void setCompNames(List<String> compNames) {
		this.compNames = compNames;
	}
	
	

	public String setUpDashboard(){

		int totalClosed = 0;
		int totalPursued = 0;
		int totalSavingPotential = 0;
		int industrialCount = 0;
		int organisationalCount = 0;
		int functionalCount = 0;
		int individualCount = 0;
		int individualAspiredCount = 0;
		int organisationalAspiredCount = 0;
		int functionalAspiredCount = 0;
		int industrialAspiredCount = 0;
		int totalSavingIdeaPotential = 0;
		int actualizeSavingChallengeRsMil = 0;
		String companyName = (String)getSession().get("loggedInUserCompanyName");
		ChallengeMaster chalBank;
		List<ChallengeMaster> objList = new ArrayList<ChallengeMaster>();
		List<ChallengeMaster> objChallengList = new ArrayList<ChallengeMaster>();

		//getting User from login session
		Employee employee = getSessionEmployee();
		//employee.getUserSettings().setActiveToolBar("FORM"); // flaming hack.

		//CHECK FOR DIFFERENT DASHBOARD FOR THE I7 AND OTHERS.

		//	Employee emp = updateUserCompRole(employee);
		if(employee!= null)
			System.out.println("*** Company ****"+companyName);
		System.out.println("*** user Role *****"+employee.getUserRole());
		//Added to retain the dashboard for User Role - I7, Multiple company view
		if(!session.containsKey("userRole")) {
			session.put("userRole", getSessionEmployee().getUserRole());
		}
		userRole = (String) session.get("userRole");

		System.out.println("*** userRole *****"+userRole);

		//Setting up Challenges matrix :: Pursued/Aspired


		objList = getChallengeBankBo().getChallengeById(companyName, employee);
		
		objChallengList = getChallengeBankBo().getTotalSavingAcrossCompanywithCompanyList();



		int totalSavingRsMil = 0;
		//Added to display the Company List in the dropdown for I7 Role
		compNames = new ArrayList<String>();
		for(ChallengeMaster challenge: objChallengList){
			totalSavingRsMil = totalSavingRsMil + Integer.parseInt(challenge.getQuant_meas_success());
			if(!compNames.contains(challenge.getCompany_id()))
				compNames.add(challenge.getCompany_id());
		}
		session.put("savingCount", totalSavingRsMil);


		if(objList!=null)
		
		{

		Iterator<ChallengeMaster> it = objList.iterator();

		while(it.hasNext()){



			chalBank = (ChallengeMaster)it.next();



			if("Successfully Scaleup".equalsIgnoreCase(chalBank.getChallenge_status())){
				totalClosed++;
			}
			if("Successfully Scaleup".equalsIgnoreCase(chalBank.getChallenge_status()) && String.valueOf(chalBank.getQuant_meas_success()) != null && Integer.parseInt(chalBank.getQuant_meas_success()) != 0){
				//actualizeSavingChallengeRsMil = actualizeSavingChallengeRsMil + chalBank.getActulizedSavingRsMil();
			}
			if(CHALLENGETYPEINDUS.equalsIgnoreCase(chalBank.getChallenge_type())){
				industrialAspiredCount++;
			}
			if(CHALLENGETYPEORGAN.equalsIgnoreCase(chalBank.getChallenge_type())){
				organisationalAspiredCount++;
			}
			if(CHALLENGETYPEFUNTCION.equalsIgnoreCase(chalBank.getChallenge_type())){
				functionalAspiredCount++;
			}
			if(CHALLENGETYPEINDIV.equalsIgnoreCase(chalBank.getChallenge_type())){
				individualAspiredCount++;
			}
			if(!chalBank.getChallenge_status().contains(CHALLENGEOPEN)){
				
				if(CHALLENGETYPEINDUS.equalsIgnoreCase(chalBank.getChallenge_type())){
					industrialCount++;
				}
				if(CHALLENGETYPEORGAN.equalsIgnoreCase(chalBank.getChallenge_type())){
					organisationalCount++;
				}
				if(CHALLENGETYPEFUNTCION.equalsIgnoreCase(chalBank.getChallenge_type())){
					functionalCount++;
				}
				if(CHALLENGETYPEINDIV.equalsIgnoreCase(chalBank.getChallenge_type())){
					individualCount++;
				}
			}
			if(!chalBank.getChallenge_status().equalsIgnoreCase(CHALLENGEOPEN) && !chalBank.getChallenge_status().equalsIgnoreCase(CHALLENGESCALEDUP) && !chalBank.getChallenge_status().equalsIgnoreCase(CHALLENGEDROPPED))
			{
				totalPursued++;
			}
			totalSavingPotential = Integer.parseInt(chalBank.getQuant_meas_success()) + totalSavingPotential;
			//totalSavingPotential = Integer.parseInt(chaBank.getPotentialRsMil()) + totalSavingPotential;
		}
		
	
		}
		chaMatrixVO.setTotalchaClosed(get3DigitCount(totalClosed));
		chaMatrixVO.setTotalChaPersured(get3DigitCount(totalPursued));
		
		if(objList==null)
		chaMatrixVO.setTotalChaInBank(get3DigitCount(0));
		else
			chaMatrixVO.setTotalChaInBank(get3DigitCount(objList.size()));


		//Added for TOTAL SAVING come from IDEA Bank--- Started
		List<String> challengeNos = new ArrayList<String>();
		int rows_count;
		List<ChallengeMaster> challenge = null;
		try {
			challenge = getChallengeBankBo().getChallengeBankListForCompany(employee);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Fixed till here
		for (Iterator<ChallengeMaster> iterator = challenge.iterator(); iterator.hasNext();) {
			ChallengeMaster challenges = (ChallengeMaster) iterator.next();
			if(challenges.getChallenge_status() != null && challenges.getChallenge_status().contains(""))
				challengeNos.add(challenges.getChallenge_id());
			//ideaBanks.addAll(bankBO.getChallengeIdeas(challenge.getId()));
		}
		List<IdeaMaster> ideaList = new ArrayList<IdeaMaster>();
		List<IdeaMaster> tempIdeaList = new ArrayList<IdeaMaster>();
		try {
			if(challengeNos.size() > 0) {
				tempIdeaList = getChallengeBankBo().getChallengeIdeas(challengeNos);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(IdeaMaster ideaBank : tempIdeaList){
			/*if(ideaBank.getIdeaStatus().contains("Closed") ||ideaBank.getIdeaStatus().contains("Scale-Up")){

			} */
			if(ideaBank.getIdea_status().contains("Closed") ){
				actualizeSavingChallengeRsMil = actualizeSavingChallengeRsMil + Integer.parseInt(ideaBank.getImpact_value());

			}
			if(ideaBank.getIdea_status().contains("Prototyping") || ideaBank.getIdea_status().contains("Closed") ){
				ideaList.add(ideaBank);
			}
		}
		System.out.println("size" + ideaList.size());

		for(IdeaMaster idea: ideaList){
			totalSavingIdeaPotential = totalSavingIdeaPotential + Integer.parseInt(idea.getImpact_value());
		}
		totalSavingPotential = totalSavingIdeaPotential;
		//Added for TOTAL SAVING come from IDEA Bank--- Ended		
		chaMatrixVO.setSavingActualized(get3DigitCount((int)convertINRtoMilion(actualizeSavingChallengeRsMil)));
		chaMatrixVO.setSavingPotential(get3DigitCount((int)convertINRtoMilion(totalSavingPotential)));
		chaMatrixVO.setIndustrialCount(get3DigitCount(industrialCount));
		chaMatrixVO.setOrganisationalCount(get3DigitCount(organisationalCount));
		chaMatrixVO.setFunctionalCount(get3DigitCount(functionalCount));
		chaMatrixVO.setIndividualCount(get3DigitCount(individualCount));
		chaMatrixVO.setFunctionalAspiredCount(String.valueOf(functionalAspiredCount));
		chaMatrixVO.setIndividualAspiredCount(String.valueOf(individualAspiredCount));
		chaMatrixVO.setIndustrialAspiredCount(String.valueOf(industrialAspiredCount));
		chaMatrixVO.setOrganisationalAspiredCount(String.valueOf(organisationalAspiredCount));

		// Setting up People Capability matrix :: Aspired
		Manpower  = loadManpowerMatrixData(getSessionEmployee());
		
		//Setting up People Capability matrix :: Actual:- 
		manpowerEmp = loadEmployeeData(employee , Manpower);
		
		if("catagory".equalsIgnoreCase(loadPieChart)){
			pieChartName = "loadCategoryBasedPieChart";
		}else{
			pieChartName = "resultPieChart";
		}
		
		return SUCCESS;
	}

	public String setUpPersonalTrackerDashboard(){
	
		
		int challengesPursued = 0;
		int ideaProtyping = 0;
		int ideaImplemented = 0;
		Long savingPotential = new Long(0);
		Long totalSavingIdeaPotential = new Long(0);
		Long savingActualized = new Long(0);
		//getting User from login session
		Employee employee = getSessionEmployee();

		List<ChallengeMaster> objList = getChallengeBankBo().getChallengeForUserId(employee);
		
		if(objList!=null)
		{
			
		
		Iterator<ChallengeMaster> it = objList.iterator();
		challengesPursued = objList.size();
		while(it.hasNext()){
			ChallengeMaster chaBank = new ChallengeMaster();
			chaBank = it.next();
			if(chaBank.getChallenge_status().contains("Scaleup")){
				savingActualized = Integer.parseInt(chaBank.getQuant_meas_success()) + savingActualized;
				//challengesPursued++;
			}
			if(chaBank.getChallenge_status().contains("Pursued")){
				//challengesPursued++;
			}else if(chaBank.getChallenge_status().contains("Idea")){
				//ideaGenerated ++;
			}else if(chaBank.getChallenge_status().contains("Prototype")){
				//ideaProtyping ++;
			}else if(chaBank.getChallenge_status().contains("Scaleup")){
				//ideaImplemented ++;
			}
			
		}

		}
		
		else 
			{
				challengesPursued = 0;
				savingActualized = Long.parseLong("0");
			}
		

		@SuppressWarnings("unchecked")
		List<IdeaMaster> ideas = getChallengeBankBo().getALLIdeasForEmployee( employee );

		if(!(ideas==null || ideas.size()==0))
		{
			
		
		for(IdeaMaster idea: ideas){
			if(idea.getIdea_status().contains("Closed")){
				ideaImplemented++;
			}
			if(idea.getIdea_status().contains("Prototyping")){
				ideaProtyping++;
				//savingPotential = Integer.parseInt(idea.getImpactValue()) + savingPotential;
			}
		}
		trackerVO.setIdeaGenerated(ideas.size());
		
		}
		else
			{
				ideaImplemented = 0;
				ideaProtyping = 0;
				trackerVO.setIdeaGenerated(0);
			}
		
		trackerVO.setChallengesPursued(challengesPursued);		
		trackerVO.setIdeaImplemented(ideaImplemented);
		trackerVO.setIdeaProtyping(ideaProtyping);

		@SuppressWarnings("unchecked")
		List<IdeaMaster> ideaList = getChallengeBankBo().getALLIdeaSavingForEmployee( employee );
		
		if(ideaList.size()!=0)
		{

		for(IdeaMaster idea: ideaList){
			totalSavingIdeaPotential = totalSavingIdeaPotential + Integer.parseInt(idea.getImpact_value());
		}
		} else 
			totalSavingIdeaPotential = Long.parseLong("0");
		savingPotential = totalSavingIdeaPotential;
		trackerVO.setSavingPotential(savingPotential.toString());
		trackerVO.setSavingActualized(savingActualized.toString());
		return SUCCESS;
	}

	public String loadPersonalTracker(){
		int challengesPursued = 0;
		int ideaGenerated = 0;
		int ideaProtyping = 0;
		int ideaImplemented = 0;
		Long savingPotential = new Long(0);
		Long totalSavingIdeaPotential = new Long(0);
		//getting User from login session
		Employee employee = getSessionEmployee();
		PersonalTrackerVO trackerVO = new PersonalTrackerVO();
		List<ChallengeMaster> objList = getChallengeBankBo().getChallengeForUserId(employee);
		Iterator<ChallengeMaster> it = objList.iterator();
		while(it.hasNext()){
			ChallengeMaster chaBank = new ChallengeMaster();
			chaBank = it.next();
			if(chaBank.getChallenge_status().contains("Pursued")){
				challengesPursued++;
			}else if(chaBank.getChallenge_status().contains("Idea")){
				ideaGenerated ++;
			}else if(chaBank.getChallenge_status().contains("Prototype")){
				ideaProtyping ++;
			}else if(chaBank.getChallenge_status().contains("Scaleup")){
				ideaImplemented ++;
			}
			savingPotential = Integer.parseInt(chaBank.getQuant_meas_success()) + savingPotential;
		}
		trackerVO.setChallengesPursued(challengesPursued);
		trackerVO.setIdeaGenerated(ideaGenerated);
		trackerVO.setIdeaImplemented(ideaImplemented);
		trackerVO.setIdeaProtyping(ideaProtyping);

		@SuppressWarnings("unchecked")
		List<IdeaMaster> ideaList = getChallengeBankBo().getALLIdeaSavingForEmployee( employee );

		for(IdeaMaster idea: ideaList){
			trackerVO.setIdeaGenerated(ideaList.size()); // confirm otherwise comment

			totalSavingIdeaPotential = totalSavingIdeaPotential + Integer.parseInt(idea.getImpact_value());
		}
		savingPotential = totalSavingIdeaPotential;
		trackerVO.setSavingPotential(get3DigitCount((int)convertINRtoMilion(savingPotential.intValue())));

		return SUCCESS;
	}


	// For load testing. Uncomment upper methods
	private Employee getSessionEmployee() {
		Employee emp =(Employee)getSession().get("user");
		if(emp== null){
			emp = new Employee();
			emp = employee; // For LoadTesting
		}
		return emp;
	}

	private String getSessionCompany() {
		return (String)getSession().get("companyName");    	
	}

	public String loadPieChart() throws Exception
	{

		List<ChallengeMaster> objList = getChallengeBankBo().getChallengeById(getSessionCompany(),getSessionEmployee());
		int totalPotentialSaving = 0;
		Map<String, Integer> objMap = new HashMap<String, Integer>(); 
		for(ChallengeMaster company : objList){
			if(objMap.containsKey(company.getCompany_id())){
				int potentialCom = objMap.get(company.getCompany_id());
				objMap.put(company.getCompany_id(), Integer.parseInt(company.getQuant_meas_success())+potentialCom);
				company.setQuant_meas_success((objMap.get(company.getCompany_id()).toString()));
			}
			objMap.put(company.getCompany_id(), Integer.parseInt(company.getQuant_meas_success()));
		}
		for (Entry<String, Integer> entry : objMap.entrySet())
		{
			ChartData chartData = new ChartData();		
			chartData.setLabel(challengeBankBo.getCompanybyId(entry.getKey()));
			chartData.setValue(Integer.parseInt(get3DigitCount((int) convertINRtoMilion(entry.getValue()))));
			chart.addChartData(chartData);
			totalPotentialSaving  = totalPotentialSaving + entry.getValue();
		}
		
		chart.setLabel("Contribution by companies at Anand (in Million)");
		return SUCCESS;
	}

	public String loadCategoryWisePieChart() throws Exception
	{
		List<ChallengeMaster> objList = getChallengeBankBo().getChallengeById(getSessionCompany(), getSessionEmployee());
		int totalPotentialSaving = 0;
		Map<String, Integer> objMap = new HashMap<String, Integer>(); 
		for(ChallengeMaster company : objList){
			if(objMap.containsKey(company.getChallenge_category())){
				int potentialCom = objMap.get(company.getChallenge_category());
				objMap.put(company.getChallenge_category(), Integer.parseInt(company.getQuant_meas_success())+potentialCom);
				company.setQuant_meas_success((objMap.get(company.getChallenge_category()).toString()));
			}
			objMap.put(company.getChallenge_category(), Integer.parseInt(company.getQuant_meas_success()));
		}
		for (Entry<String, Integer> entry : objMap.entrySet())
		{
			ChartData chartData = new ChartData();
			chartData.setLabel(entry.getKey());
			chartData.setValue(Integer.parseInt(get3DigitCount((int) convertINRtoMilion(entry.getValue()))));
			chart.addChartData(chartData);
			totalPotentialSaving  = totalPotentialSaving + entry.getValue();
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		chart.setLabel("CategoryWise Saving in "+year+" - "+(year+1)+" Rs "+(get3DigitCount((int) convertINRtoMilion(totalPotentialSaving))+" Million"));
		return SUCCESS;
	}

	private double convertINRtoMilion(int inrrupee) {
		return inrrupee*.000001;
	}

	public Manpower loadManpowerMatrixData(Employee employee)
	{
		Manpower = new Manpower();
		int fs1Count = 0;
		int fs2Count = 0;
		int fs3Count = 0;
		int starterCount = 0;
		

		Employee empObj = new Employee();
		List<Employee> empList = getManPowerBo().getManPowerData();
		Iterator<Employee> it = empList.iterator();
		while(it.hasNext()){

			empObj = it.next();

			if("I7".equalsIgnoreCase(employee.getUserRole())){

				if(CAPABILITYFS1.equalsIgnoreCase(empObj.getCapability()))
					fs1Count++;
				
				else if(CAPABILITYFS2.equalsIgnoreCase(empObj.getCapability()))
					fs2Count++;
				
				else if(CAPABILITYFS3.equalsIgnoreCase(empObj.getCapability()))
					fs3Count++;
				
				else if(CAPABILITYSTARTER.equalsIgnoreCase(empObj.getCapability()))
					starterCount++;
				
								
			}else if(!"I7".equalsIgnoreCase(employee.getUserRole())){
				
			    if(empObj.getCompanyID().equalsIgnoreCase(employee.getCompanyID()))
			    {
			    	if(CAPABILITYFS1.equalsIgnoreCase(empObj.getCapability()))
						fs1Count++;
					
					else if(CAPABILITYFS2.equalsIgnoreCase(empObj.getCapability()))
						fs2Count++;
					
					else if(CAPABILITYFS3.equalsIgnoreCase(empObj.getCapability()))
						fs3Count++;
					
					else if(CAPABILITYSTARTER.equalsIgnoreCase(empObj.getCapability()))
						starterCount++;
			    }
			    	
			}
		}
		//currentObj.setManpowerCount(String.valueOf(manpowerCount));
		Manpower.setFs1(String.valueOf(fs1Count));
		Manpower.setFs2(String.valueOf(fs2Count));
		Manpower.setFs3(String.valueOf(fs3Count));
		Manpower.setInnovattionStarter(String.valueOf(starterCount));
		return Manpower;
	}

	public Manpower loadEmployeeData(Employee employee, Manpower Manpower){
		
		Manpower manPowerData = new Manpower();
		//List<Employee> objList;
		//objList = getEmployeeBo().getEmployeeData(employee);
		List<Employee> objList = getManPowerBo().getManPowerData();
	//	Manpower.setManpowerCount(String.valueOf(objList.size()));
		Iterator<Employee> it = objList.iterator();
		int starterCount = 0;
		int fs1Count = 0;
		int fs2Count = 0;
		int fs3Count = 0;
		while(it.hasNext()){
			Employee empObj = new Employee();
			empObj = it.next();
			if(!"I7".equals(empObj.getUserRole())){
			}
			if("I7".equalsIgnoreCase(employee.getUserRole())){
				//innovators++;
				if("Innovation Starter".equalsIgnoreCase(empObj.getCapability())) {
					starterCount++;
				}
				if("FS1".equalsIgnoreCase(empObj.getCapability())) {
					fs1Count++;
				}
				if("FS2".equalsIgnoreCase(empObj.getCapability())) {
					fs2Count++;
				}
				if("FS3".equalsIgnoreCase(empObj.getCapability())) {
					fs3Count++;
				}

			}else if(!"I7".equalsIgnoreCase(employee.getUserRole()) && empObj.getCompanyID().equalsIgnoreCase(employee.getCompanyID())) {

				if("Innovation Starter".equalsIgnoreCase(empObj.getCapability())) {
					starterCount++;
				}
				if("FS1".equalsIgnoreCase(empObj.getCapability()))
					fs1Count++;
				if("FS2".equalsIgnoreCase(empObj.getCapability()))
					fs2Count++;
				if("FS3".equalsIgnoreCase(empObj.getCapability()))
					fs3Count++;
			}

		}
		manPowerData.setFs1(String.valueOf(fs1Count));
		manPowerData.setFs2(String.valueOf(fs2Count));
		manPowerData.setFs3(String.valueOf(fs3Count));
		manPowerData.setInnovattionStarter(String.valueOf(starterCount));
		return manPowerData;
	}

	public Chart getChart() {
		return chart;
	}

	public String get3DigitCount(int count){
		String number = String.valueOf(count);
		if(number!=null){
			while(number.length()<3){
				number="0"+number;
			}
			if(number.length()>3){
				number = number.substring(0, 3);
			}
		}
		return number;
	}

	public void setErrorMessage(String errorMessage) {
		this.m_errorMessage = errorMessage;
		addActionError(errorMessage);
	}
	public ChallengeMaster getChallenge() {
		return challenge;
	}

	public void setChallenge(ChallengeMaster challenge) {
		this.challenge = challenge;
	}

	public String getFormattedTime(Date date) {
		String formattedTime = "";
		if(date != null ) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a, MM/dd/yyyy");
			formattedTime = dateFormat.format(date);
		}
		return formattedTime;
	}
	public ChallengeBankBO getChallengeBankBo() {
		return challengeBankBo;
	}

	public void setChallengeBankBo(ChallengeBankBO challengeBankBo) {
		this.challengeBankBo = challengeBankBo;
	}

	public ChallengeMatrixVO getChaMatrixVO() {
		return chaMatrixVO;
	}

	public void setChaMatrixVO(ChallengeMatrixVO chaMatrixVO) {
		this.chaMatrixVO = chaMatrixVO;
	}

	public ManPowerBO getManPowerBo() {
		return manPowerBo;
	}

	public void setManPowerBo(ManPowerBO manPowerBo) {
		this.manPowerBo = manPowerBo;
	}

	public Manpower getManpowerVO() {
		return Manpower;
	}

	public void setManpowerVO(Manpower Manpower) {
		this.Manpower = Manpower;
	}

	public EmployeeBO getEmployeeBo() {
		return employeeBo;
	}

	public void setEmployeeBo(EmployeeBO employeeBo) {
		this.employeeBo = employeeBo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Manpower getManpowerEmp() {
		return manpowerEmp;
	}

	public void setManpowerEmp(Manpower manpowerEmp) {
		this.manpowerEmp = manpowerEmp;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	@Override
	public void validate() {
	}
}