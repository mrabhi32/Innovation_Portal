package com.vgil.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import com.core.action.login.LoginAction;
import com.core.model.authentication.User;
import com.vgil.bo.EmployeeBO;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.CompanyMaster;
import com.vgil.model.DepartmentMaster;
import com.vgil.model.DesignationMaster;
import com.vgil.model.EmpLoginInfo;
import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;

import static com.vgil.model.ApplicationConstants.*;




public class InnovationLoginAction extends LoginAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InnovationLoginAction.class);
	private Employee employee;
	private EmpLoginInfo employeelogininfo;
	private String targetPage;
	List<CompanyMaster> objCompanyGroup = new ArrayList<CompanyMaster>();	
	String companyName;
	List<PlantMaster> plantName = new ArrayList<PlantMaster>();
	List<String> roleList = new ArrayList<String>();
	List<String> companyList = new ArrayList<String>();
	List<String> designationList = new ArrayList<String>();	
	List<String> departmentList = new ArrayList<String>();	
	List<ApplicationSettings> allRoles = new ArrayList<ApplicationSettings>();
	List<String> anchorRoles = new ArrayList<String>();
	List<String> roles = new ArrayList<String>();
	List<String> allCompanies = new ArrayList<String>();
	List<PlantMaster> objPlants = new ArrayList<PlantMaster>();
	List<CompanyMaster> companyMaster = new ArrayList<CompanyMaster>();
	List<PlantMaster> plantMaster = new ArrayList<PlantMaster>();
	List<DepartmentMaster> departmentMaster = new ArrayList<DepartmentMaster>();
	List<DesignationMaster> designationMaster = new ArrayList<DesignationMaster>();
	private EmployeeBO employeeBO;
	private String company = null;
	User user;

	ChallengeMaster challenge;




	public EmpLoginInfo getEmployeelogininfo() {
		return employeelogininfo;
	}

	public void setEmployeelogininfo(EmpLoginInfo employeelogininfo) {
		this.employeelogininfo = employeelogininfo;
	}

	public List<String> getDesignationList() {
		return designationList;
	}

	public void setDesignationList(List<String> designationList) {
		this.designationList = designationList;
	}

	public List<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<String> departmentList) {
		this.departmentList = departmentList;
	}

	public EmployeeBO getEmployeeBO() {
		return employeeBO;
	}

	public void setCompanyList(List<String> companyList) {
		this.companyList = companyList;
	}

	public void setEmployeeBO(EmployeeBO employeeBO) {
		this.employeeBO = employeeBO;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
		if(employee != null){
			setUser(employee.getUser());
		}
	}

	public Employee getEmployee() {
		return employee;
	}

	public String login(){
		String loginStatus =LOGIN_FAIL;
		String userID;
		clearExistingSession();

		try{
			userID = checkLoginUser();
			if (!(userID == null ||(userID.equals(""))))
			{
				loginStatus = LOGIN_PASS;

				employee = employeeBO.getEmployeeDetails(userID);
				setSessionVariables();
				targetPage = "dashboardAction";

			}
			else targetPage = "loginFail";				

		}catch(Exception e){
			logger.error(e.getMessage(), e);
			addActionError("User Login failed.Please try again.");
			return ERROR;
		}
		
		if(loginStatus.equals(LOGIN_PASS))
		return loginStatus;
		
		else 
			{
			addActionError("In-Correct Username or Password Provided. Please check and try again.");
			return ERROR;
			}
	}

	private void clearExistingSession()
	{
		if(getSession().containsKey("user"))
			getSession().remove("user");
		if(getSession().containsKey("userRole"))
			getSession().remove("userRole");
	}

	private String checkLoginUser()
	{
		String returnValue = null;
		try {
			String username = getUser().getUserCredentials().getUserName();
			String password = getUser().getUserCredentials().getPassword();
			returnValue = employeeBO.getLoginStatus(username,password);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if(returnValue==null || returnValue.equals(" "))
		{
			return null;
		}
		else return returnValue;
	}
	public List<String> getCompanyList() {
		return companyList;
	}



	public void setSessionVariables()
	{
		String currentItem;
		List<String> plants = new ArrayList<String>();
		Map<String,List<String>> allPLants = new HashMap<String,List<String>>();
		try
		{
			getSession().put("user", employee);
			companyName = employeeBO.getCompanyByID(employee.getCompanyID());
			getSession().put("loggedInUserCompanyName", companyName);

			//This will get all the Roles in the System.
			allRoles = employeeBO.getAllRoles(); 

			companyMaster = employeeBO.getAllCompanies();
			plantMaster   = employeeBO.getAllPlants();
			departmentMaster = employeeBO.getAllDepartments();
			designationMaster = employeeBO.getAllDesignations();
			getSession().put("MasterCompanyData", companyMaster); 
			getSession().put("MasterPlantData", plantMaster); 
			getSession().put("MasterDepartmentData", departmentMaster); 
			getSession().put("MasterDesignationData", designationMaster); 

			for(int j=0;j<departmentMaster.size();j++)
			{

				departmentList.add(departmentMaster.get(j).getDepartment_name());

			}

			for(int j=0;j<designationMaster.size();j++)
			{

				designationList.add(designationMaster.get(j).getDesignation_name());

			}
			getSession().put("designationData", designationList);
			getSession().put("departmentData", departmentList);


			if(employee.getUserRole().equalsIgnoreCase(ADMIN_USER))
			{


				objCompanyGroup = companyMaster;
				objPlants = plantMaster;
				for(int i=0;i<objCompanyGroup.size();i++)
				{
					allCompanies.add(objCompanyGroup.get(i).getCompany_name());
					for(int j=0;j<objPlants.size();j++)
					{
						if(objCompanyGroup.get(i).getCompany_id().equals(objPlants.get(j).getCompany_id()))
						{
							plants.add(objPlants.get(j).getPlant_name());
						}
					}

					allPLants.put(objCompanyGroup.get(i).getCompany_name(),plants);
					
				}	
							
				
				getSession().put("UserAccesibleCompany", allCompanies); 
				getSession().put("UserAccesiblePlants", allPLants);

				for(int i=0;i<allRoles.size();i++)
				{
					currentItem  = allRoles.get(i).getValue();
					roles.add(currentItem);			
				}
				//An admin Can add any Role employee in the System. 
				getSession().put("UserAccessibleRole", roles);

			}	
			else if(employee.getUserRole().equalsIgnoreCase(ANCHOR_USER))
			{

				allCompanies.add(companyName);
				getSession().put("UserAccesibleCompany", allCompanies);


				//Anchor can add only Plant Anchors, I5 and Innovator
				for(int i=0;i<allRoles.size();i++)
				{
					currentItem  = allRoles.get(i).getValue();
					if(!(currentItem.equals(I7_USER) || currentItem.equals(ADMIN_USER)))
					{
						anchorRoles.add(currentItem);
					}

				}
				getSession().put("UserAccessibleRole", anchorRoles);

				plantName = employeeBO.getPlantsbyCompanyId(employee.getCompanyID());

				for(int j=0;j<plantName.size();j++)
				{

					plants.add(plantName.get(j).getPlant_name());

				}
				
				
				allPLants.put(companyName, plants);
				getSession().put("UserAccesiblePlants",allPLants);

			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Caught ");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String registerUser(){
		try {
			String user_id;
			String validation;
			Employee emp = new Employee();
			EmpLoginInfo empLogin = new EmpLoginInfo();
			Employee loggedInUser = new Employee();


			String companyName,plantName,designationName,departmentName;
			List<CompanyMaster> companyMaster = new ArrayList<CompanyMaster>();
			List<PlantMaster> plantMaster = new ArrayList<PlantMaster>();
			List<DesignationMaster> designationMaster = new ArrayList<DesignationMaster>();
			List<DepartmentMaster> departmentMaster = new ArrayList<DepartmentMaster>();
			int serial_no;


			String userExists = employeeBO.checkExistingUser(this.employee.getEmailID());
			
			loggedInUser = (Employee)getSession().get("user");

			companyMaster = (List<CompanyMaster>)getSession().get("MasterCompanyData");
			plantMaster = (List<PlantMaster>)getSession().get("MasterPlantData");
			designationMaster = (List<DesignationMaster>)getSession().get("MasterDesignationData");
			departmentMaster = (List<DepartmentMaster>)getSession().get("MasterDepartmentData");

			if(userExists.equals(USER_NOT_EXISTS))
			{

				serial_no = employeeBO.getLatestSerial_no(emp);
				user_id = USERCONSTANT+(serial_no+1);

				emp.setSerial_no(serial_no+1);
				emp.setUser_id(user_id);
				validation = validate(this.employee,this.employeelogininfo);
				if(validation.equals(VALIDATION_PASS))
				{
					
				
						emp.setFirstName(this.employee.getFirstName());
						emp.setLastName(this.employee.getLastName());
		
						emp.setEmailID(this.employee.getEmailID());
						emp.setUserRole(this.employee.getUserRole());
						emp.setContactNo(this.employee.getContactNo());
		
						companyName = this.employee.getCompanyName();
						for(int i=0;i<companyMaster.size();i++)
						{
							if(companyMaster.get(i).getCompany_name().equals(companyName))
							{
								emp.setCompanyID(companyMaster.get(i).getCompany_id());
							}
						}
		
		
						plantName = this.employee.getPlantName();
						for(int i=0;i<plantMaster.size();i++)
						{
							if(plantMaster.get(i).getPlant_name().equals(plantName))
							{
								emp.setPlantID(plantMaster.get(i).getPlant_id());
							}
						}
		
		
						departmentName = this.employee.getDepartmentName();
						for(int i=0;i<departmentMaster.size();i++)
						{
							if(departmentMaster.get(i).getDepartment_name().equals(departmentName))
							{
								emp.setDepartmentID(departmentMaster.get(i).getDepartment_id());
							}
						}
		
						designationName = this.employee.getDesignationName();
						for(int i=0;i<designationMaster.size();i++)
						{
							if(designationMaster.get(i).getDesignation_name().equals(designationName))
							{
								emp.setDesignationID(designationMaster.get(i).getDesignation_id());
							}
						}
		
						if(INNOVATOR_USER.equalsIgnoreCase(this.employee.getUserRole())){
							emp.setCapability(CAPABILITYSTARTER);
						}
						if(ANCHOR_USER.equalsIgnoreCase(this.employee.getUserRole())){
							emp.setCapability(CAPABILITYFS1);
						}
						if(I5_USER.equalsIgnoreCase(this.employee.getUserRole())){
							emp.setCapability(CAPABILITYFS3);
						}
		
						emp.setUserStatus(STATUS_ACTIVE);
						emp.setReasonForInactivation(NOTAPPLICABLE);
						emp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
						emp.setCreatedBy(loggedInUser.getUser_id());
						emp.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
						emp.setLastUpdatedBy(loggedInUser.getUser_id());
		
						serial_no = employeeBO.getLatestSerial_no(empLogin);
						empLogin.setSerial_no(serial_no+1);
						empLogin.setUser_id(user_id);
						empLogin.setUserName(emp.getEmailID());
						empLogin.setPassword(this.employeelogininfo.getPassword());
						empLogin.setCreatedDate(new Timestamp(System.currentTimeMillis()));
						empLogin.setCreatedBy(emp.getCreatedBy());
						empLogin.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
						empLogin.setLastUpdatedBy(emp.getLastUpdatedBy());
		
						employeeBO.saveOrUpdateEmployee(emp);
						employeeBO.saveOrUpdateEmployee(empLogin);
				}
				else 
				{
					throw new Exception("Cannot add Employee. The details are: \n"+ validation);
				}
			}

			else 
			{
				throw new Exception("User Already Exists. Please try with a different email Id");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("User Registration failed.Please try again.Reason:- "+e.getMessage());
			try{
				super.deleteUserAccount();

			}catch(Exception e1){
				logger.error(e.getMessage(), e);
			}
			return ERROR;
		}
		addActionMessage("User Registration accepted.Email has been send to Admin to activate your account.");


		return SUCCESS;
	}

	public String deleteUserAccount() {
		try {
			setUser(employee.getUser());
			super.deleteUserAccount();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("User account deletion failed.");
			return ERROR;
		}
		return SUCCESS;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public String uploadEmployee(){


		List<CompanyMaster> companyMaster = new ArrayList<CompanyMaster>();
		List<PlantMaster> plantMaster = new ArrayList<PlantMaster>();
		List<DepartmentMaster> departmentMaster = new ArrayList<DepartmentMaster>();
		List<DesignationMaster> designationMaster = new ArrayList<DesignationMaster>();
		Employee employee;
		EmpLoginInfo empLogin;
		String user_id;
		int lineCount = 0;
		String strLine;
		int serial_no;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String validation;
	



		String returnValue = null;
		File uploadedFile = getFile();
		try {

			Employee loggedInUser = (Employee)getSession().get("user");


			String errorString = employeeBO.parseFile(uploadedFile,getSession());

			fileReader = new FileReader(uploadedFile);
			bufferedReader = new BufferedReader(fileReader);

			String userExists;

			if(BLANKSPACE.equals(errorString))
			{

				while ((strLine = bufferedReader.readLine()) != null) {

					//To Read the file from Line 2, since Line 1 will contain the headers.
					if(lineCount!=0){


						employee = new Employee();
						empLogin = new EmpLoginInfo();

						serial_no = employeeBO.getLatestSerial_no(employee);
						
						String[] employeeString = strLine.split(",");

						userExists = employeeBO.checkExistingUser(employeeString[2]);

						if(userExists.equals(USER_NOT_EXISTS))
						{

							user_id = USERCONSTANT+(serial_no+1);

							employee.setSerial_no(serial_no+1);
							employee.setUser_id(user_id);

							employee.setFirstName(employeeString[0]);
							employee.setLastName(employeeString[1]);
							employee.setEmailID(employeeString[2]);
							employee.setUserRole(employeeString[3]);
							employee.setContactNo(employeeString[4]);

							companyMaster = (List<CompanyMaster>)session.get("MasterCompanyData");
							plantMaster = (List<PlantMaster>)session.get("MasterPlantData");
							designationMaster = (List<DesignationMaster>)session.get("MasterDesignationData");
							departmentMaster = (List<DepartmentMaster>)session.get("MasterDepartmentData");

							for(int i=0;i<companyMaster.size();i++)
							{
								if(companyMaster.get(i).getCompany_name().equals(employeeString[5]))
								{
									employee.setCompanyID(companyMaster.get(i).getCompany_id());
								}
							}



							for(int i=0;i<plantMaster.size();i++)
							{
								if(plantMaster.get(i).getPlant_name().equals(employeeString[6]))
								{
									employee.setPlantID(plantMaster.get(i).getPlant_id());
								}
							}


							for(int i=0;i<departmentMaster.size();i++)
							{
								if(departmentMaster.get(i).getDepartment_name().equals(employeeString[7]))
								{
									employee.setDepartmentID(departmentMaster.get(i).getDepartment_id());
								}
							}


							for(int i=0;i<designationMaster.size();i++)
							{
								if(designationMaster.get(i).getDesignation_name().equals(employeeString[8]))
								{
									employee.setDesignationID(designationMaster.get(i).getDesignation_id());
								}
							}


							employee.setReasonForInactivation(employeeString[9]);


							if(INNOVATOR_USER.equalsIgnoreCase(employee.getUserRole())){
								employee.setCapability(CAPABILITYSTARTER);
							}
							if(ANCHOR_USER.equalsIgnoreCase(employee.getUserRole())){
								employee.setCapability(CAPABILITYFS1);
							}
							if(I5_USER.equalsIgnoreCase(employee.getUserRole())){
								employee.setCapability(CAPABILITYFS3);
							}

							employee.setUserStatus(STATUS_ACTIVE);
							employee.setCreatedDate(new Timestamp(System.currentTimeMillis()));
							employee.setCreatedBy(loggedInUser.getUser_id());
							employee.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
							employee.setLastUpdatedBy(loggedInUser.getUser_id());

							serial_no = employeeBO.getLatestSerial_no(empLogin);
							empLogin.setSerial_no(serial_no+1);
							empLogin.setUser_id(user_id);
							empLogin.setUserName(employee.getEmailID());
							empLogin.setPassword(employeeString[11]);
							empLogin.setCreatedDate(new Timestamp(System.currentTimeMillis()));
							empLogin.setCreatedBy(loggedInUser.getCreatedBy());
							empLogin.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));
							empLogin.setLastUpdatedBy(loggedInUser.getLastUpdatedBy());
							
							validation = validate(employee,empLogin);
							
							if(validation.equals(VALIDATION_PASS))
							{
													
							employeeBO.saveOrUpdateEmployee(employee);						
							System.out.println("Employee with User Id "+employee.getUser_id()+" added");
							
							employeeBO.saveOrUpdateEmployee(empLogin);
							System.out.println("Employee Credentials with User Id "+empLogin.getUser_id()+" added");
							
							}
							else 
							{
								throw new Exception("Cannot add Employee. The details are: \n"+ validation);
							}
						}
						else 
						{
							throw new Exception("Email ID for "+employeeString[0]+" "+employeeString[1]+" already in Use. Please try specifying a different Email ID.");
						}


						

					}
					lineCount ++;

				}




			}




			else if(!errorString.equals(BLANKSPACE))
			{
				getSession().put("ErrorMessage", errorString);
				addActionError("File Upload Not Successful. Please click on the Error Log link to see the details.");
				returnValue = ERROR;
			}
			else
			{
				addActionMessage("Employees data updloaded successfully");
				returnValue = SUCCESS;
			}

			bufferedReader.close();

		}
		catch(Exception e)
		{
			
			getSession().put("ErrorMessage", e.getMessage());
			addActionError("User Registration failed.Please click on the Log File for Details.");


			return ERROR;

		}

		return returnValue;

	}
	
	public String changePassword() {
		logger.info("Entering changePassword ....");
		try {
			if(getSession().containsKey("user") && getSession().get("user") != null){
				employee = (Employee)(getSession().get("user"));
				return changePassword(employee.getUser().getUserCredentials());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("Password not Changed.Please try again.");
			return ERROR;
		}
		logger.info("Exiting changePassword ....");
		addActionError("Server Error. Please try again.");
		return ERROR;
	}

	@SuppressWarnings("unchecked")
	public String setupUserRegisterData() {
		try {

			if(getSession().containsKey("user") && getSession().get("user") != null)
			{
				roleList = (ArrayList<String>)getSession().get("UserAccessibleRole");
				companyList = (ArrayList<String>)getSession().get("UserAccesibleCompany");
				departmentList = (ArrayList<String>)getSession().get("departmentData");
				designationList = (ArrayList<String>)getSession().get("designationData");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//addActionError("Password not Changed.Please try again.");
			return ERROR;
		}
		logger.info("Exiting setupUserRegisterData ....");
		return "register";
	}
	
	
	private String validate(Employee emp, EmpLoginInfo empLogin)
	{
		String returnValue="";
		
		
		 String expression;
	     CharSequence checkString;
	     Pattern pattern; 
	     Matcher matcher;
	        
		
		//Validations for the First Name
		
	     
	    //To check for only Alphabets	    
	    checkString = emp.getFirstName();    
	    
	    expression = "^[a-zA-Z]*$";
	    pattern = Pattern.compile(expression);
	    matcher = pattern.matcher(checkString);
	    
		if(matcher.matches())
		{
			returnValue = VALIDATION_PASS;
		}
		else 
		{
			returnValue = "First name cannot contain Numbers or special characters. \n";
		}
		
		//To check for First alphabet capital and Rest all small Case
		for(int iter = 0;iter < emp.getFirstName().toCharArray().length; iter++)
		{
			if(iter == 0)
			{
				if(Character.isUpperCase(emp.getFirstName().toCharArray()[iter]))
				{
					returnValue = VALIDATION_PASS;
				}	
				
				else
				{
					returnValue = "First Alphabet should be capital for the First Name and Rest all should be Small (Lower Case) \n";
				}
			}
			
			else 
			{
				if(!Character.isUpperCase(emp.getFirstName().toCharArray()[iter]))
				{
					returnValue = VALIDATION_PASS;
				}
				else
				{
					returnValue = "First Alphabet should be capital for the First Name and Rest all should be Small (Lower Case) \n";
				}
			}
		}
			
		
		
		
				
		
		
		//Validations for the Last Name
		
	     
	    //To check for only Alphabets	    
	    checkString = emp.getLastName();    
	    
	    expression = "^[a-zA-Z]*$";
	    pattern = Pattern.compile(expression);
	    matcher = pattern.matcher(checkString);
	    
		if(matcher.matches())
		{
			returnValue = VALIDATION_PASS;
		}
		else 
		{
			returnValue = "Last name cannot contain Numbers or special characters. \n";
		}
		
		//To check for First Alphabet capital and Rest all small Case
		for(int iter = 0;iter < emp.getLastName().toCharArray().length; iter++)
		{
			if(iter == 0)
			{
				if(Character.isUpperCase(emp.getLastName().toCharArray()[iter]))
				{
					returnValue = VALIDATION_PASS;
				}	
				
				else
				{
					returnValue = "First Alphabet should be capital for the Last Name and Rest all should be Small (Lower Case) \n";
				}
			}
			
			else 
			{
				if(!Character.isUpperCase(emp.getLastName().toCharArray()[iter]))
				{
					returnValue = VALIDATION_PASS;
				}
				else
				{
					returnValue = "First Alphabet should be capital for the Last Name and Rest all should be Small (Lower Case) \n";
				}
			}
		}
		
		
		
		
		
		
		//To check for Email	    
	    checkString = emp.getEmailID();    
	    
	    expression = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}";
	    pattern = Pattern.compile(expression);
	    matcher = pattern.matcher(checkString);
	    
		if(matcher.matches())
		{
			returnValue = VALIDATION_PASS;
		}
		else 
		{
			returnValue = "Email Value is Not Correct. Please check. \n";
		}
		
		
		
		
		
		
		//To check for Password	    
	    checkString = empLogin.getPassword();    
	    
	    expression = "^^([a-zA-Z0-9@*#$%^&*]{8,15})$";
	    pattern = Pattern.compile(expression);
	    matcher = pattern.matcher(checkString);
	    
		if(matcher.matches())
		{
			returnValue = VALIDATION_PASS;
		}
		else 
		{
			returnValue = "Password supplied is Not Correct. Please check. The password should be of 8-15 characters and can contain Upper Case, Lowercase Letters, Numbers and special characters like ! @ # $ % ^ & * \n";
		}
		
		
		
		
		//To check for Phone Number	    
	    checkString = emp.getContactNo();    
	    
	    expression = "^[1-9]{2}[0-9]{8}$";
	    pattern = Pattern.compile(expression);
	    matcher = pattern.matcher(checkString);
	    
		if(matcher.matches())
		{
			returnValue = VALIDATION_PASS;
		}
		else 
		{
			returnValue = "Contact Number should be a 10 digit number without any space or a special character.\n";
		}
				
		
		return returnValue;
	}



	public String setupPlant() {

		return "setupPlant";
	}

	public String setupAspireValues() {

		return "setupAspireValues";
	}

	public String setupCompany() {
		return "setupCompany";
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getTargetPage() {
		return targetPage;
	}


	public ChallengeMaster getChallenge() {
		return challenge;
	}

	public void setChallenge(ChallengeMaster challenge) {
		this.challenge = challenge;
	}

	public List<CompanyMaster> getObjCompanyGroup() {
		return objCompanyGroup;
	}

	public void setObjCompanyGroup(List<CompanyMaster> objCompanyGroup) {
		this.objCompanyGroup = objCompanyGroup;
	}



	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}