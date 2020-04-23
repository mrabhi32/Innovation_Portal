package com.vgil.action;

import static com.vgil.model.ApplicationConstants.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import com.core.action.BaseAction;
import com.core.bo.ApplicationSettingsBo;
import com.core.model.applicationSettings.ApplicationSettings;
import com.vgil.bo.ChallengeBankBO;
import com.vgil.bo.EmployeeBO;
import com.vgil.bo.ManPowerBO;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.CompanyMaster;
import com.vgil.model.DepartmentMaster;
import com.vgil.model.DesignationMaster;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.Manpower;
import com.vgil.model.PlantMaster;

public class EditAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	/** The LOGGER *. */
	private static final Logger logger = Logger.getLogger(EditAction.class);
	private IdeaMaster ideaVo;
	private long id;


	private String challengeId;
	private String challenge;
	private String challengeType;
	private String challengePriority;
	private String challengeCategory;
	private String quantMeasSuccess;
	private String challengeStatus;
	private String challengeEndDate;
	private String challengeStartDate;
	private String reasonForDrop;



	private String ideaStatus;
	private String userId;
	private String errorStatus;
	private String successStatus;
	private String userRole;

	private String emailID;
	private String contactNo;
	private String companyName;
	private String plantName;
	private String departmentName;
	private String designationName;
	private String userStatus;
	private String reasonForInactivation;
	private Employee loggedInUser;

	private String companayDesc;
	private String plantDesc;
	private int innovatorsValue;
	private int innovatorStartersValue;
	private int innovatorsBuddiesValue;

	List<String> objCompanyGroup = new ArrayList<String>();	

	//@Autowired
	private ChallengeBankBO challengeBankBo;

	//@Autowired 
	private ApplicationSettingsBo applicationSettingsBo;


	//@Autowired
	ManPowerBO manpowerBo;

	//@Autowired
	private EmployeeBO employeeBo;


	public String saveOrUpdateEmployee(){
		logger.info("saveOrUpdateUser Start");
		//setTargetPage("user");
		try {
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	@SuppressWarnings("unchecked")
	public String changeChallengeStatus(){

	    boolean foundElement = false;
		String returnValue =ERROR_RETURN_STRING;

		
		List<PlantMaster> plant;
		List<String> type;
		List<String> category;
		List<CompanyMaster> companyList;
		List<String> priority;
		List<String> status;
		


		ChallengeMaster challengeMaster = null;
		try {

			loggedInUser = (Employee) getSession().get("user");
			challengeMaster = challengeBankBo.loadChallenge(challengeId);
			
			
			//If the User has changed the challenge Status
			if(companyName==null && plantName==null && challengeType==null && challengeCategory==null && challenge==null && challengePriority==null && quantMeasSuccess==null && challengeStatus!=null && reasonForDrop==null)
			{
				
				challengeStatus = challengeStatus.trim();
				if(challengeStatus.equals(CHALLENGEDROPPED))
				{
					returnValue =ERROR_RETURN_STRING;
					addActionError("Please add a Reason for Challenge Drop first.");
				}
				
				else
				{
					challengeStatus = challengeStatus.trim();
					status = (ArrayList<String>) getSession().get("ChallengeStatusList");
					if(challengeMaster.getChallenge_status().equals(CHALLENGEDROPPED))
					{
						challengeMaster.setReason_for_drop(NOTAPPLICABLE);
					}
					for(int i=0;i<status.size();i++)
					{
						if(challengeStatus.equals(status.get(i)))
						{
							
							challengeMaster.setChallenge_status(status.get(i));
							foundElement = true;
						}
	
					}
	
					if(foundElement == false)
					{
						throw new Exception("Invalid Company choosed. Please contact Administrator for Assistance.");
					}
					
					
					if(challengeStatus.equals(CHALLENGECLOSED))
					{
						challengeMaster.setChallenge_end_date(new Timestamp(System.currentTimeMillis()));
					}
	
					challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
					challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
					returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
					if(returnValue.equals("SUCCESS"))
						returnValue = SUCCESS_RETURN_STRING;

				}				
				
			}
			
			
			
			//If the User has changed the challenge Drop Reason
			if(companyName==null && plantName==null && challengeType==null && challengeCategory==null && challenge==null && challengePriority==null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop!=null)
			{
				
					reasonForDrop = reasonForDrop.trim();
					
					if(reasonForDrop.isEmpty())
					{
						challengeMaster.setReason_for_drop(NOTAPPLICABLE);
					}
					else
					{
						challengeMaster.setReason_for_drop(reasonForDrop);
						challengeMaster.setChallenge_status(CHALLENGEDROPPED);	
					}					
					challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
					challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
					returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
					if(returnValue.equals("SUCCESS"))
						returnValue = SUCCESS_RETURN_STRING;

				}			
			
			if(!(challengeMaster.getChallenge_status().equals(CHALLENGEOPEN)))
			{


			//If the User has changed the companyName
			if(companyName!=null && plantName==null && challengeType==null && challengeCategory==null && challenge==null && challengePriority==null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop==null)
			{
				companyName = companyName.trim();
				companyList = (ArrayList<CompanyMaster>) getSession().get("MasterCompanyData");
				for(int i=0;i<companyList.size();i++)
				{
					if(companyName.equals(companyList.get(i).getCompany_name()))
					{
						challengeMaster.setCompany_id(companyList.get(i).getCompany_id());
						foundElement = true;
					}

				}

				if(foundElement == false)
				{
					throw new Exception("Invalid Company choosed. Please contact Administrator for Assistance.");
				}

				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;

			}


			//If the User has changed the Plant Name
			if(companyName==null && plantName!=null && challengeType==null && challengeCategory==null && challenge==null && challengePriority==null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop==null)
			{
				plantName = plantName.trim();
				plant = (ArrayList<PlantMaster>) getSession().get("MasterPlantData");
				for(int i=0;i<plant.size();i++)
				{
					if(plantName.equals(plant.get(i).getPlant_name()))
					{
						challengeMaster.setPlant_id(plant.get(i).getPlant_id());
						foundElement = true;
					}

				}

				if(foundElement == false)
				{
					throw new Exception("Invalid Plant choosed. Please contact Administrator for Assistance.");
				}
				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;

			}
			
			
			//If the User has changed the Challenge Type
			if(companyName==null && plantName==null && challengeType!=null && challengeCategory==null && challenge==null && challengePriority==null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop==null)
			{
				challengeType = challengeType.trim();
				type = (ArrayList<String>) getSession().get("ChallengeTypeList");
				for(int i=0;i<type.size();i++)
				{
					if(challengeType.equals(type.get(i)))
					{
						challengeMaster.setChallenge_type(type.get(i));
						foundElement = true;
					}

				}

				if(foundElement == false)
				{
					throw new Exception("Invalid Challenge Type choosed. Please contact Administrator for Assistance.");
				}
				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;

			}

			//If the User has changed the Challenge Category
			if(companyName==null && plantName==null && challengeType==null && challengeCategory!=null && challenge==null && challengePriority==null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop==null)
			{
				challengeCategory = challengeCategory.trim();
				category = (ArrayList<String>) getSession().get("ChallengeCategoryList");
				for(int i=0;i<category.size();i++)
				{
					if(challengeCategory.equals(category.get(i)))
					{
						challengeMaster.setChallenge_category(category.get(i));
						foundElement = true;
					}

				}

				if(foundElement == false)
				{
					throw new Exception("Invalid Challenge Category choosed. Please contact Administrator for Assistance.");
				}
				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;

			}
			
			
			//If the User has changed the Challenge Description
			if(companyName==null && plantName==null && challengeType==null && challengeCategory==null && challenge!=null && challengePriority==null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop==null)
			{
				challenge = challenge.trim();
				challengeMaster.setChallenge(challenge);
				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;

			}
			
			
			//If the User has changed the Challenge Priority
			if(companyName==null && plantName==null && challengeType==null && challengeCategory==null && challenge==null && challengePriority!=null && quantMeasSuccess==null && challengeStatus==null && reasonForDrop==null)
			{
				challengePriority = challengePriority.trim();
				priority = (ArrayList<String>) getSession().get("ChallengePriorityList");
				for(int i=0;i<priority.size();i++)
				{
					if(challengeCategory.equals(priority.get(i)))
					{
						challengeMaster.setPriority(priority.get(i));
						foundElement = true;
					}

				}

				if(foundElement == false)
				{
					throw new Exception("Invalid Challenge Category choosed. Please contact Administrator for Assistance.");
				}
				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;


			}
			
			
			//If the User has changed the Challenge Description
			if(companyName==null && plantName==null && challengeType==null && challengeCategory==null && challenge==null && challengePriority==null && quantMeasSuccess!=null && challengeStatus==null && reasonForDrop==null)
			{
				quantMeasSuccess = quantMeasSuccess.trim();
				challengeMaster.setQuant_meas_success(quantMeasSuccess);
				challengeMaster.setLast_updated_by(loggedInUser.getUser_id());
				challengeMaster.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
				returnValue = challengeBankBo.saveOrUpdateChallenge(challengeMaster);
				if(returnValue.equals("SUCCESS"))
					returnValue = SUCCESS_RETURN_STRING;


			}

			}
			
			else
			{
				returnValue = ERROR_RETURN_STRING;
				addActionError("The Challenge cannot be Modified since it is Not in Open/New state. Please Modify the challenge Status to Modify.");
			}




		}catch(Exception e)
		{
			addActionError("Error Modifying the challenge. Error is :- "+e.getMessage());
			returnValue = ERROR_RETURN_STRING;
		}			
		return returnValue;
	}


	public String editIdea(){
		try{
			if(ideaVo != null)
				getChallengeBankBo().saveOrUpdateIdea(ideaVo);
		} catch(Exception e){
			logger.error(e.getMessage(), e);
			addActionError("Challenge not saved.Please try again.");
		}

		addActionMessage("Idea Saved successfully");
		return "success";
	}

	public String changeIdeaStatus(){
		IdeaMaster ideaBank = new IdeaMaster();
		Employee employee = ((Employee)(getSession().get("user")));
		try {
			IdeaMaster idea = getChallengeBankBo().loadIdea(Long.parseLong(ideaVo.getIdea_id()));
			if(ideaVo.getIdea_status() != null && !"".equals(ideaVo.getIdea_status().trim()) && idea.getIdea_status() != null){
				if(idea.getIdea_status().contains("Prototyping")){
					if(ideaVo.getIdea_status().contains("Catogorized")){
						addActionError("Status not changed. Wrong status selection.");
						return ERROR;
					}
				}else if(idea.getIdea_status().contains("Escalated")){

				}else if(idea.getIdea_status().contains("Scale")){

				}else if(idea.getIdea_status().contains("ITransfer")){

				}else if(idea.getIdea_status().contains("Closed")){
					if(ideaVo.getIdea_status().contains("Catogorized")){
						//addActionError("Wrong status set");
						addActionError("Status not changed. Wrong status selection.");
						return ERROR;
					}
					else if(ideaVo.getIdea_status().contains("Prototyping")){
						//addActionError("Wrong status set");
						addActionError("Status not changed. Wrong status selection.");
						return ERROR;
					}else if(ideaVo.getIdea_status().contains("Escalated")){
						//addActionError("Wrong status set");
						addActionError("Status not changed. Wrong status selection.");
						return ERROR;
					}else if(ideaVo.getIdea_status().contains("Scale")){
						//addActionError("Wrong status set");
						addActionError("Status not changed. Wrong status selection.");
						return ERROR;
					}else if(ideaVo.getIdea_status().contains("ITransfer")){
						//addActionError("Wrong status set");
						addActionError("Status not changed. Wrong status selection.");
						return ERROR;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
		ideaBank.setIdea_id(ideaVo.getIdea_id());
		ideaBank.setIdea_status(ideaVo.getIdea_status());
		ideaBank.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
		ideaBank.setUser_id(employee.getUser_id());
		try{
			getChallengeBankBo().saveOrUpdateIdea(ideaBank);
		} catch(Exception e){
			logger.error(e.getMessage(), e);
			addActionError("Idea not saved.Please try again.");
		}

		//addActionMessage("Idea status changed successfully");
		addActionMessage("Idea status updated");
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String editEmployee(){

		boolean foundElement = false;
		String returnValue =ERROR_RETURN_STRING;

		ArrayList<CompanyMaster> company;
		ArrayList<PlantMaster> plant;
		ArrayList<DepartmentMaster> department;
		ArrayList<DesignationMaster> designation;

		try{

			loggedInUser = (Employee) getSession().get("user");
			if(userId != null && !"".equals(userId.trim())) {

				Employee employee = employeeBo.getEmployeeDetails(userId);

				if(emailID != null && userRole==null && contactNo ==null && companyName == null && plantName == null && departmentName == null && designationName==null  && userStatus == null && reasonForInactivation == null)
				{
					employee.setEmailID(emailID);		
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);	
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole!=null && contactNo ==null && companyName == null && plantName == null && departmentName == null && designationName==null  && userStatus == null && reasonForInactivation == null)
				{
					employee.setUserRole(userRole);		

					if(INNOVATOR_USER.equalsIgnoreCase(employee.getUserRole())){
						employee.setCapability(CAPABILITYSTARTER);
					}
					if(ANCHOR_USER.equalsIgnoreCase(employee.getUserRole())){
						employee.setCapability(CAPABILITYFS1);
					}
					if(I5_USER.equalsIgnoreCase(employee.getUserRole())){
						employee.setCapability(CAPABILITYFS3);
					}

					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);	
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole==null && contactNo !=null && companyName == null && plantName == null && departmentName == null && designationName==null  && userStatus == null && reasonForInactivation == null)
				{
					employee.setContactNo(contactNo);		
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);	
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole==null && contactNo ==null && companyName != null && plantName == null && departmentName == null && designationName==null  && userStatus == null && reasonForInactivation == null)
				{
					companyName = companyName.trim();
					company = (ArrayList<CompanyMaster>) getSession().get("MasterCompanyData");
					for(int i=0;i<company.size();i++)
					{
						if(companyName.equals(company.get(i).getCompany_name()))
						{
							employee.setCompanyID(company.get(i).getCompany_id());
							foundElement = true;
						}

					}

					if(foundElement == false)
					{
						throw new Exception("Invalid Company choosed. Please contact Administrator for Assistance.");
					}
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);	
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole==null && contactNo ==null && companyName == null && plantName != null && departmentName == null && designationName==null  && userStatus == null && reasonForInactivation == null)
				{
					plantName = plantName.trim();
					plant = (ArrayList<PlantMaster>) getSession().get("MasterPlantData");
					for(int i=0;i<plant.size();i++)
					{
						if(plantName.equals(plant.get(i).getPlant_name()))
						{
							employee.setPlantID(plant.get(i).getPlant_id());
							foundElement = true;
						}

					}

					if(foundElement == false)
					{
						throw new Exception("Invalid Plant choosed. Please contact Administrator for Assistance.");
					}
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole==null && contactNo ==null && companyName == null && plantName == null && departmentName != null && designationName==null  && userStatus == null && reasonForInactivation == null)
				{
					departmentName = departmentName.trim();
					department = (ArrayList<DepartmentMaster>) getSession().get("MasterDepartmentData");
					for(int i=0;i<department.size();i++)
					{
						if(departmentName.equals(department.get(i).getDepartment_name()))
						{
							employee.setDepartmentID(department.get(i).getDepartment_id());
							foundElement = true;
						}

					}

					if(foundElement == false)
					{
						throw new Exception("Invalid Department choosed. Please contact Administrator for Assistance.");
					}
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);	
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole==null && contactNo ==null && companyName == null && plantName == null && departmentName == null && designationName !=null  && userStatus == null && reasonForInactivation == null)
				{
					designationName = designationName.trim();
					designation = (ArrayList<DesignationMaster>) getSession().get("MasterDesignationData");
					for(int i=0;i<designation.size();i++)
					{
						if(designationName.equals(designation.get(i).getDesignation_name()))
						{
							employee.setDesignationID(designation.get(i).getDesignation_id());
							foundElement = true;
						}

					}

					if(foundElement == false)
					{
						throw new Exception("Invalid Designation choosed. Please contact Administrator for Assistance.");
					}
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);	
					returnValue = SUCCESS_RETURN_STRING;
				}

				if(emailID == null && userRole==null && contactNo ==null && companyName == null && plantName == null && departmentName == null && designationName ==null && userStatus == null && reasonForInactivation != null)
				{


                    if(reasonForInactivation.isEmpty())
                    {
                    	employee.setReasonForInactivation(NOTAPPLICABLE);
                    }
                    else
                    {
                    	employee.setReasonForInactivation(reasonForInactivation);	
                    }
					
					employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
					employee.setLastUpdatedBy(loggedInUser.getUser_id());
					employeeBo.saveOrUpdateEmployee(employee);
					returnValue = SUCCESS_RETURN_STRING;


				}

				if(emailID == null && userRole==null && contactNo ==null && companyName == null && plantName == null && departmentName == null && designationName ==null && userStatus != null  )
				{

					if(userStatus.equals(STATUS_INACTIVE) && employee.getReasonForInactivation().equals(NOTAPPLICABLE))
					{			

						throw new Exception("Reason for Inactivation should be added, before you deactivate a User.");

					}

					if(userStatus.equals(STATUS_INACTIVE) && !employee.getReasonForInactivation().equals(NOTAPPLICABLE))
					{			

						employee.setUserStatus(userStatus);
						employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
						employee.setLastUpdatedBy(loggedInUser.getUser_id());
						employeeBo.saveOrUpdateEmployee(employee);
						returnValue = SUCCESS_RETURN_STRING;

					}

					if(!userStatus.equals(STATUS_INACTIVE) && !employee.getReasonForInactivation().equals(NOTAPPLICABLE))
					{			

						employee.setUserStatus(userStatus);
						employee.setReasonForInactivation(NOTAPPLICABLE);
						employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
						employee.setLastUpdatedBy(loggedInUser.getUser_id());
						employeeBo.saveOrUpdateEmployee(employee);
						returnValue = SUCCESS_RETURN_STRING;

					}

				}			

			} 
		}catch(Exception e){
			//logger.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			addActionError(" "+e.getMessage());
			returnValue = ERROR_RETURN_STRING;

		}

		return returnValue;
	}


	public String getFormattedTime(Date date) {
		String formattedTime = "";
		if(date != null ) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a, MM/dd/yyyy");
			formattedTime = dateFormat.format(date);
		}
		return formattedTime;
	}


	public String savePlant() {
		ApplicationSettings applicationSettings = new ApplicationSettings();
		applicationSettings.setCodeType("PLANT");
		/*applicationSettings.setCode(company);
		applicationSettings.setValue(plant);*/
		applicationSettings.setDescription(plantDesc);
		try {
			getApplicationSettingsBo().setApplicationSettings(applicationSettings);
		} catch (Exception e) {
			e.printStackTrace();
			getCompanyList();
			addActionError("Plant not added. Please try again latter.");
			return ERROR;
		}
		getCompanyList();
		addActionMessage("Plant Added successfully");
		return "setupPlant";
	}

	public ManPowerBO getManpowerBo() {
		return manpowerBo;
	}

	public void setManpowerBo(ManPowerBO manpowerBo) {
		this.manpowerBo = manpowerBo;
	}

	public void getCompanyList() {
		try {

			List<ApplicationSettings> objRole = getApplicationSettingsBo().getLookupByTypeCompany("Victorgroup");
			for (ApplicationSettings applicationSettings : objRole) {
				if("Victorgroup".equalsIgnoreCase(applicationSettings.getCodeType())){
					objCompanyGroup.add(applicationSettings.getValue());
				}
				/*if("PLANT".equalsIgnoreCase(applicationSettings.getCodeType())){
					objPlantGroup.add(applicationSettings.getValue());
				}
				if("ROLE".equalsIgnoreCase(applicationSettings.getCodeType())){
					objRole.add(applicationSettings.getValue());
				}*/
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("Error in fetching Company List .Please try again.");
		}
	}
	public String saveAspireValues() {
		Manpower manPower = new Manpower();

		manPower.setInnovattionStarter(String.valueOf(innovatorStartersValue));
		/*getManpowerBo().saveOrUpdateManPower(manPower);
		getCompanyList();*/
		//getManpowerBo().getManPowerData(company);
		addActionMessage("Aspire Values Saved successfully");
		return "setupAspireValues";
	}

	public String saveCompany() {
		ApplicationSettings applicationSettings = new ApplicationSettings();
		applicationSettings.setCodeType("Victorgroup");
		/*applicationSettings.setCode(company);
		applicationSettings.setValue(company);*/
		applicationSettings.setDescription(companayDesc);
		try {
			getApplicationSettingsBo().setApplicationSettings(applicationSettings);
			//	getCompanyList();
		} catch (Exception e) {
			e.printStackTrace();
			//	getCompanyList();
			addActionError("Plant not added. Please try again latter.");
			return ERROR;
		}
		addActionMessage("Company Added successfully");
		return "setupCompany";
	}

	@Override
	public void validate() {


	}

	public IdeaMaster getIdeaVo() {
		return ideaVo;
	}

	public void setIdeaVo(IdeaMaster ideaVo) {
		this.ideaVo = ideaVo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(String challengeId) {
		this.challengeId = challengeId;
	}

	/*public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}*/

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public String getChallengeType() {
		return challengeType;
	}

	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}

	public String getChallengePriority() {
		return challengePriority;
	}

	public void setChallengePriority(String challengePriority) {
		this.challengePriority = challengePriority;
	}

	public String getChallengeCategory() {
		return challengeCategory;
	}

	public void setChallengeCategory(String challengeCategory) {
		this.challengeCategory = challengeCategory;
	}

	public String getQuantMeasSuccess() {
		return quantMeasSuccess;
	}

	public void setQuantMeasSuccess(String quantMeasSuccess) {
		this.quantMeasSuccess = quantMeasSuccess;
	}



	public String getChallengeEndDate() {
		return challengeEndDate;
	}

	public void setChallengeEndDate(String challengeEndDate) {
		this.challengeEndDate = challengeEndDate;
	}

	public String getChallengeStartDate() {
		return challengeStartDate;
	}

	public void setChallengeStartDate(String challengeStartDate) {
		this.challengeStartDate = challengeStartDate;
	}

	public String getIdeaStatus() {
		return ideaStatus;
	}

	public void setIdeaStatus(String ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getSuccessStatus() {
		return successStatus;
	}

	public void setSuccessStatus(String successStatus) {
		this.successStatus = successStatus;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getReasonForInactivation() {
		return reasonForInactivation;
	}

	public void setReasonForInactivation(String reasonForInactivation) {
		this.reasonForInactivation = reasonForInactivation;
	}

	public Employee getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Employee loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public String getCompanayDesc() {
		return companayDesc;
	}

	public void setCompanayDesc(String companayDesc) {
		this.companayDesc = companayDesc;
	}

	public String getPlantDesc() {
		return plantDesc;
	}

	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}

	public int getInnovatorsValue() {
		return innovatorsValue;
	}

	public void setInnovatorsValue(int innovatorsValue) {
		this.innovatorsValue = innovatorsValue;
	}

	public int getInnovatorStartersValue() {
		return innovatorStartersValue;
	}

	public void setInnovatorStartersValue(int innovatorStartersValue) {
		this.innovatorStartersValue = innovatorStartersValue;
	}

	public int getInnovatorsBuddiesValue() {
		return innovatorsBuddiesValue;
	}

	public void setInnovatorsBuddiesValue(int innovatorsBuddiesValue) {
		this.innovatorsBuddiesValue = innovatorsBuddiesValue;
	}

	public List<String> getObjCompanyGroup() {
		return objCompanyGroup;
	}

	public void setObjCompanyGroup(List<String> objCompanyGroup) {
		this.objCompanyGroup = objCompanyGroup;
	}

	public ChallengeBankBO getChallengeBankBo() {
		return challengeBankBo;
	}

	public void setChallengeBankBo(ChallengeBankBO challengeBankBo) {
		this.challengeBankBo = challengeBankBo;
	}

	public ApplicationSettingsBo getApplicationSettingsBo() {
		return applicationSettingsBo;
	}

	public void setApplicationSettingsBo(ApplicationSettingsBo applicationSettingsBo) {
		this.applicationSettingsBo = applicationSettingsBo;
	}

	public EmployeeBO getEmployeeBo() {
		return employeeBo;
	}

	public void setEmployeeBo(EmployeeBO employeeBo) {
		this.employeeBo = employeeBo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public String getChallengeStatus() {
		return challengeStatus;
	}

	public void setChallengeStatus(String challengeStatus) {
		this.challengeStatus = challengeStatus;
	}

	public String getReasonForDrop() {
		return reasonForDrop;
	}

	public void setReasonForDrop(String reasonForDrop) {
		this.reasonForDrop = reasonForDrop;
	}





}
