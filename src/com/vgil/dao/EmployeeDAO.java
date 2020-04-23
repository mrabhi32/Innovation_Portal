package com.vgil.dao;

import java.util.List;
import java.util.TreeMap;

import com.vgil.model.ApplicationSettings;
import com.vgil.model.CompanyMaster;
import com.vgil.model.DepartmentMaster;
import com.vgil.model.DesignationMaster;
import com.vgil.model.EmpLoginInfo;
import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;

public interface EmployeeDAO {
	public boolean saveOrUpdateEmployee(Employee empInfo) throws Exception;
	public TreeMap<String, Object> getEmployeeById(Employee empInfo);
	public Employee getEmployeeDetails(String userId) throws Exception;
	public List<Employee> getEmployeeData(Employee empInfo);
	public int countByCriteriaForUser(String searchField, String searchOper,String searchString);
	public List<Employee> findByCriteriaForUser(String searchField,String searchOper, String searchString, int from, int to);
	public Integer getSameCompanyPlantEmployeeCount(String company,String plant) throws Exception;
	public List<Employee> getSameCompanyPlantEmployeeList(String company,String plant,int from, int to) throws Exception;
	public List<CompanyMaster> getAllCompanies() throws Exception;
	public String getCompanybyID(String id) throws Exception;
	public String getUserLogin(String userName, String password) throws Exception;
	public List<ApplicationSettings> getAllRoles() throws Exception;
	public List<PlantMaster> getPlantsbycompanyId(String companyID);
	public List<PlantMaster>getAllPlants();
	public List<DesignationMaster>getAllDesignations();
	public List<DepartmentMaster>getAllDepartments();
	public int getLatestSerial_no(Employee emp);
	public int getLatestSerial_no(EmpLoginInfo emp);
	public boolean saveOrUpdateEmployee(EmpLoginInfo employee) throws Exception;
	public String checkExistingUser (String loginID) throws Exception;	
	
}
