package com.vgil.bo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.core.action.login.LoginAction;
import com.core.bo.ApplicationSettingsBo;
import com.vgil.bo.EmployeeBO;
import com.vgil.dao.EmployeeDAO;
import com.vgil.dao.impl.EmployeeDAOImpl;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.CompanyMaster;
import com.vgil.model.DepartmentMaster;
import com.vgil.model.DesignationMaster;
import com.vgil.model.EmpLoginInfo;
import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;
import static com.vgil.model.ApplicationConstants.*;

public class EmployeeBOImpl extends LoginAction implements EmployeeBO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(EmployeeBOImpl.class);

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	//@Autowired
	ApplicationSettingsBo applicationSettingsBo;

	public ApplicationSettingsBo getApplicationSettingsBo() {
		return applicationSettingsBo;
	}
	public void setApplicationSettingsBo(ApplicationSettingsBo applicationSettingsBo) {
		this.applicationSettingsBo = applicationSettingsBo;
	}
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}
	public void setEmployeeDAO(EmployeeDAO employeeDao) {
		this.employeeDAO = employeeDao;
	}

	@Override
	public void saveOrUpdateEmployee(Employee employee) throws Exception{
		employeeDAO.saveOrUpdateEmployee(employee);
	}
	@Override
	public TreeMap<String, Object> getEmployeeById(Employee employee) {
		TreeMap<String,Object> responseMap= new TreeMap<String,Object>();
		responseMap = employeeDAO.getEmployeeById(employee);
		return responseMap;
	}
	@Override
	public Employee getEmployeeDetails(String userId) throws Exception {
		return employeeDAO.getEmployeeDetails(userId);
	}

	@Override
	public List<Employee>  getEmployeeData(Employee employee) {
		List<Employee> responseList= new ArrayList<Employee>();
		responseList = employeeDAO.getEmployeeData(employee);
		return responseList;
	}

	@SuppressWarnings("unchecked")
	public String validateEntries (BufferedReader employeeList,Map<String,Object> session,int total_lines){

		String error = BLANKSPACE;
		String strLine;
		int secondLine=0;
		int lineCount=1;
		Employee checkEmployee;
		EmpLoginInfo checkLoginInfo;
		


		List<String> allRoles;
		List<String> allCompanies;
		List<String> allDepartments;
		List<String> allDesignations;
		List<String> plants;
		Map<String,List<String>> allPlants; 



		try
		{
           
            
            if(total_lines > 1)
            {
			while ((strLine = employeeList.readLine()) != null) {

				//To Read the file from Line 2, since Line 1 will contain the headers.
				if(secondLine!=0){

					
					checkEmployee = new Employee();
					checkLoginInfo = new EmpLoginInfo();
					lineCount++;
					String[] employeeString = strLine.split(",");
					
					//To check the number of Columns in the attached file.
					if(employeeString.length != NUMBEROFENTRIESINEMPLOYEEUPLOADFILE){
						error = error + "Data not correct. Total "+NUMBEROFENTRIESINEMPLOYEEUPLOADFILE+" entries expected. Error on line no "+lineCount+".\n";
					}



					checkEmployee.setFirstName(employeeString[0]);
					//Checking Validation for First Name
					if(BLANKSPACE.equals(checkEmployee.getFirstName().trim())){
						error = error + "File upload failed. First Name cannot be empty. Error on line no "+lineCount+".\n";
					}



					checkEmployee.setLastName(employeeString[1]);
					//Checking Validation for Last Name
					if(BLANKSPACE.equals(checkEmployee.getLastName().trim())){
						error = error + "File upload failed. LastName can not be empty. Error on line no "+lineCount+".\n";
					}


					checkEmployee.setEmailID(employeeString[2]);
					//Checking Validation for Email Address
					if(BLANKSPACE.equals(checkEmployee.getEmailID().trim())){
						error = error + "File upload failed. Email Address can not be empty. Error on line no "+lineCount+".\n";
					}
					String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(checkEmployee.getEmailID().trim());
					if(!matcher.matches()){
						error = error + "File upload failed. Email Address Not Valid. Error on line no "+lineCount+".\n";
					}


					checkEmployee.setUserRole(employeeString[3]);
					//Checking Validation for User Role
					if(BLANKSPACE.equals(checkEmployee.getUserRole().trim())){
						error = error + "File upload failed. User role can not be empty. Error on line no "+lineCount+".\n";
					}
					allRoles = (ArrayList<String>)session.get("UserAccessibleRole");
					if(!allRoles.contains(checkEmployee.getUserRole()))
					{
						error = error + "You are not Authorized to add Users for the Role "+checkEmployee.getUserRole()+". Error on line no "+lineCount+".\n";
					}


					checkEmployee.setContactNo(employeeString[4]);
					//Checking Validation for Contact Number
					if(BLANKSPACE.equals(checkEmployee.getContactNo().trim())){
						error = error + "File upload failed. Contact number can not be empty. Error on line no "+lineCount+".\n";
					}


					checkEmployee.setCompanyName(employeeString[5]);
					//Checking Validation for Company
					if(BLANKSPACE.equals(checkEmployee.getCompanyName().trim())){
						error = error + "File upload failed. Company Name can not be empty. Error on line no "+lineCount+".\n";
					}
					allCompanies = (ArrayList<String>)session.get("UserAccesibleCompany");
					if(!allCompanies.contains(checkEmployee.getCompanyName()))
					{
						error = error + "You are not Authorized to add Users for the Company "+checkEmployee.getCompanyName()+". Error on line no "+lineCount+".\n";
					}



					checkEmployee.setPlantName(employeeString[6]);
					//Checking Validation for Company
					if(BLANKSPACE.equals(checkEmployee.getPlantName().trim())){
						error = error + "File upload failed. Plant Name can not be empty. Error on line no "+lineCount+".\n";
					}
					allPlants = (Map<String,List<String>>)session.get("UserAccesiblePlants");
					plants = (List<String>)allPlants.get(checkEmployee.getCompanyName());
					if (!plants.contains(checkEmployee.getPlantName()))
					{
						error = error + "File upload failed. The Plant "+checkEmployee.getPlantName()+" is not a part of Company's ("+checkEmployee.getCompanyName()+") Plants .Error on line no "+lineCount+".\n";					
					}


					checkEmployee.setDepartmentName(employeeString[7]);
					//Checking Validation for Company
					if(BLANKSPACE.equals(checkEmployee.getDepartmentName().trim())){
						error = error + "File upload failed. Department Name can not be empty. Error on line no "+lineCount+".\n";
					}
					allDepartments = (ArrayList<String>)session.get("departmentData");
					if (!allDepartments.contains(checkEmployee.getDepartmentName()))
					{
						error = error + "File upload failed. "+checkEmployee.getDepartmentName()+" is not a valid Department Name .Error on line no "+lineCount+".\n";					
					}



					checkEmployee.setDesignationName(employeeString[8]);
					//Checking Validation for Company
					if(BLANKSPACE.equals(checkEmployee.getDesignationName().trim())){
						error = error + "File upload failed. Designation Name can not be empty. Error on line no "+lineCount+".\n";
					}
					allDesignations = (ArrayList<String>)session.get("designationData");
					if (!allDesignations.contains(checkEmployee.getDesignationName()))
					{
						error = error + "File upload failed. "+checkEmployee.getDesignationName()+" is not a valid Designation Name .Error on line no "+lineCount+".\n";
					}



					checkLoginInfo.setUserName(employeeString[10]);
					//UserName should be the Email Id of the User

					if(BLANKSPACE.equals(checkLoginInfo.getUserName().trim())){
						error = error + "File upload failed. Login Username cannot be empty. Error on line no "+lineCount+".\n";
					}
					if (!checkLoginInfo.getUserName().equals(checkEmployee.getEmailID()))
					{
						error = error + "File upload failed. Login Username should be same as the email address.Error on line no "+lineCount+".\n";
					}


					checkLoginInfo.setPassword(employeeString[11]);
					//UserName should be the Email Id of the User

					if(BLANKSPACE.equals(checkLoginInfo.getPassword().trim())){
						error = error + "File upload failed. Login Password cannot be empty. Error on line no "+lineCount+".\n";
					}
					//Add Code for Invalid characters Check.


				}

				secondLine++;
			}
			
            }
            else 
            {
            	error = "There is no Datain the file. Please Upload a file with Proper Data.";
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}



		return error;


	}



	
	@Override
	public String parseFile(File uploadedFile,Map<String, Object> session) throws Exception {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String errorString;
		int lines =0;


		try {

			fileReader = new FileReader(uploadedFile);
			bufferedReader = new BufferedReader(fileReader);
			while(bufferedReader.readLine()!=null)
			{
				lines++;
			}
			
			bufferedReader = new BufferedReader(fileReader);
			errorString = validateEntries(bufferedReader,session,lines);
		}catch (Exception e) {
			logger.error("File upload failed. Error occured : " + e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}finally{
			try {
				if(bufferedReader != null )
					bufferedReader.close();
			} catch (IOException e) {
			}
			try {
				if(fileReader != null )
					fileReader.close();
			} catch (IOException e) {
			}
		}

		return errorString;
	}
	@Override
	public int countByCriteriaForEmployee(String searchField,
			String searchOper, String searchString) throws Exception {
		return employeeDAO.countByCriteriaForUser(searchField, searchOper, searchString);
	}
	@Override
	public List<Employee> findByCriteriaForEmployee(String searchField,
			String searchOper, String searchString, int from, int to)
					throws Exception {
		return employeeDAO.findByCriteriaForUser(searchField, searchOper, searchString, from, to);
	}
	@Override
	public Integer getSameCompanyPlantEmployeeCount(String company, String plant)
			throws Exception {
		return employeeDAO.getSameCompanyPlantEmployeeCount(company, plant);
	}
	@Override
	public List<Employee> getSameCompanyPlantEmployeeList(String company,
			String plant, int from, int to) throws Exception {
		// TODO Auto-generated method stub
		return employeeDAO.getSameCompanyPlantEmployeeList(company, plant, from, to);
	}
	@Override
	public List<CompanyMaster> getAllCompanies() throws Exception {

		return employeeDAO.getAllCompanies();

	}
	@Override
	public java.lang.String getCompanyByID(String id) throws Exception {

		return employeeDAO.getCompanybyID(id);
	}
	@Override
	public java.lang.String getLoginStatus(String userName, String password) throws Exception {

		return employeeDAO.getUserLogin(userName,password);
	}

	public List<ApplicationSettings> getAllRoles() throws Exception
	{

		return employeeDAO.getAllRoles();		

	}

	public List<PlantMaster> getPlantsbyCompanyId(String companyID)
	{
		return employeeDAO.getPlantsbycompanyId(companyID);
	}

	public List<PlantMaster> getAllPlants()
	{
		return employeeDAO.getAllPlants();
	}
	@Override
	public List<DesignationMaster> getAllDesignations() {

		return employeeDAO.getAllDesignations();
	}
	@Override
	public List<DepartmentMaster> getAllDepartments() {

		return employeeDAO.getAllDepartments();
	}

	public int getLatestSerial_no(Employee emp)
	{
		return employeeDAO.getLatestSerial_no(emp);
	}

	public int getLatestSerial_no(EmpLoginInfo emp)
	{
		return employeeDAO.getLatestSerial_no(emp);
	}
	@Override
	public void saveOrUpdateEmployee(EmpLoginInfo emplogin) throws Exception{
		employeeDAO.saveOrUpdateEmployee(emplogin);
	}
	@Override
	public String checkExistingUser(String loginID) throws Exception {
		
		return employeeDAO.checkExistingUser(loginID);
		
	}


}
