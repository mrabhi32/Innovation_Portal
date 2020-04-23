package com.vgil.bo;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.vgil.model.ApplicationSettings;
import com.vgil.model.CompanyMaster;
import com.vgil.model.DepartmentMaster;
import com.vgil.model.DesignationMaster;
import com.vgil.model.EmpLoginInfo;
import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;

public interface EmployeeBO {

	void saveOrUpdateEmployee(Employee emp) throws Exception;
	List<Employee> getEmployeeData(Employee emp);
	TreeMap<String, Object> getEmployeeById(Employee employee);
	Employee getEmployeeDetails(String userId) throws Exception;
	public String parseFile(File file,Map<String, Object> session) throws Exception;
	public int countByCriteriaForEmployee(String searchField,String searchOper, String searchString) throws Exception;
	public List<Employee> findByCriteriaForEmployee(String searchField,String searchOper, String searchString,int from, int to) throws Exception;
	public Integer getSameCompanyPlantEmployeeCount(String company,String plant) throws Exception;
	public List<Employee> getSameCompanyPlantEmployeeList(String company,String plant,int from, int to) throws Exception;
	public List<CompanyMaster> getAllCompanies() throws Exception;
	public String getCompanyByID(String id) throws Exception;
	String getLoginStatus(String userName, String password) throws Exception;
	public List<ApplicationSettings> getAllRoles() throws Exception;
	public List<PlantMaster>getPlantsbyCompanyId (String companyID);
	public List<PlantMaster>getAllPlants();
	public List<DesignationMaster>getAllDesignations();
	public List<DepartmentMaster>getAllDepartments();
	public int getLatestSerial_no(Employee emp);
	public int getLatestSerial_no(EmpLoginInfo emp);
	public void saveOrUpdateEmployee(EmpLoginInfo employee) throws Exception;
    public String validateEntries (BufferedReader employeeList,Map<String,Object> session,int line_count) throws Exception;
    public String checkExistingUser (String loginID) throws Exception;	
   
}
