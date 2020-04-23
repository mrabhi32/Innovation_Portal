package com.vgil.dao.impl;

import static com.vgil.model.ApplicationConstants.USERROLE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.core.dao.impl.LoginDAOImpl;
import com.vgil.dao.EmployeeDAO;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.CompanyMaster;
import com.vgil.model.DepartmentMaster;
import com.vgil.model.DesignationMaster;
import com.vgil.model.EmpLoginInfo;
import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;

import static com.vgil.model.ApplicationConstants.*;


public class EmployeeDAOImpl extends LoginDAOImpl implements EmployeeDAO{

	@Override
	public boolean saveOrUpdateEmployee(Employee emp) {
		getHibernateTemplate().saveOrUpdate(emp);
		return false;
	}

	@Override
	public TreeMap<String, Object> getEmployeeById(Employee emp) {
		//getHibernateTemplate().load(Employee.class, emp.getEmpCode());
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployeeDetails(String userId) throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		Employee user = null;
		
		List<Employee> userList =  (List<Employee>) getHibernateTemplate().findByCriteria(
		        DetachedCriteria.forClass(Employee.class)
		        .add(Restrictions.eq("user_id", userId)));
		if(userList != null && userList.size() >0){
			user = userList.get(0);
		}
		
		//getSession().put("user", user);
		return user;
	}
	
		@SuppressWarnings("unchecked")
		@Override
	public List<Employee> getEmployeeData(Employee emp) {

		List<Employee> empCompanyList = new ArrayList<Employee>();
		List<Object[]> empList;
		Employee empDetail;
		int count=0;
		 getHibernateTemplate().setCacheQueries(true);  
		if(ADMIN_USER.equalsIgnoreCase(emp.getUserRole())){
			empList  = getHibernateTemplate().find
					("select e from Employee e");
		} else {
			
			Object[] params  = {emp.getCompanyID()};
			empList = getHibernateTemplate().find
					("Select em.serial_no,em.user_id,em.firstName,  em.lastName, em.emailID,em.userRole, em.contactNo, cm.company_name, pm.plant_name,dm.department_name,dem.designation_name,em.capability,em.userStatus,em.reasonForInactivation"
							+" from Employee em, CompanyMaster cm, PlantMaster pm, DepartmentMaster dm, DesignationMaster dem"
							+" where em.companyID = cm.company_id"
							+" and em.plantID = pm.plant_id"
							+" and em.departmentID = dm.department_id"
							+" and em.designationID = dem.designation_id"
							+" and em.companyID=?",
							params);
		}
		for (Iterator<Object[]> iterator = empList.iterator(); iterator.hasNext();) {
		
			
			Object[] list = (Object[]) iterator.next();
			empDetail = new Employee();
			count++;
			empDetail.setSerial_no(count);
			
			empDetail.setUser_id(list[1].toString());
			empDetail.setFirstName(list[2].toString());
			empDetail.setLastName(list[3].toString());
			empDetail.setEmailID(list[4].toString());
			empDetail.setUserRole(list[5].toString());
			empDetail.setContactNo(list[6].toString());
			empDetail.setCompanyName(list[7].toString());
			empDetail.setPlantName(list[8].toString());
			empDetail.setDepartmentName(list[9].toString());
			empDetail.setDesignationName(list[10].toString());
			empDetail.setCapability(list[11].toString());
			empDetail.setUserStatus(list[12].toString());
			empDetail.setReasonForInactivation(list[13].toString());
			empCompanyList.add(empDetail);
		}
		
		return empCompanyList;
	}

		@Override
		public int countByCriteriaForUser(String searchField,
				String searchOper, String searchString) {
			return 0;
		}

/*		public List<Employee> findByCriteriaForEmployee(String searchField,
				String searchOper, String searchString, int from, int to) {
	        if (searchOper.equals("eq")){
	        	return (getHibernateTemplate().findByCriteria(
	    		        DetachedCriteria.forClass(Employee.class)
	    		        .add(Restrictions.eq(searchField, searchString)))).subList(from, to);
	        }
	        else if (searchOper.equals("ne"))
	        	return (getHibernateTemplate().findByCriteria(
	    		        DetachedCriteria.forClass(Employee.class)
	    		        .add(Restrictions.ne(searchField, searchString)))).subList(from, to);
	        else if (searchOper.equals("lt")) 
	        	return (getHibernateTemplate().findByCriteria(
	    		        DetachedCriteria.forClass(Employee.class)
	    		        .add(Restrictions.lt(searchField, searchString)))).subList(from, to);
	        else if (searchOper.equals("gt")) 
	        	return (getHibernateTemplate().findByCriteria(
	    		        DetachedCriteria.forClass(Employee.class)
	    		        .add(Restrictions.gt(searchField, searchString)))).subList(from, to);
	        else if (searchOper.equals("bw")) 
	        	return (getHibernateTemplate().findByCriteria(
	    		        DetachedCriteria.forClass(Employee.class)
	    		        .add(Restrictions.like(searchField, searchString + "%")))).subList(from, to);
	        else if (searchOper.equals("cn")) 
	        	return (getHibernateTemplate().findByCriteria(
	    		        DetachedCriteria.forClass(Employee.class)
	    		        .add(Restrictions.like(searchField, "%" + searchString + "%")))).subList(from, to);
			return null;
		} */

		public List<Employee> getPendingVerificationEmployee() {
			
			return null;
		}

		@Override
		public List<Employee> findByCriteriaForUser(String searchField,
				String searchOper, String searchString, int from, int to) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Integer getSameCompanyPlantEmployeeCount(String company,
				String plant) throws Exception {
			getHibernateTemplate().setCacheQueries(true);  
			return   (Integer) getHibernateTemplate().findByCriteria(
			        DetachedCriteria.forClass(Employee.class)
			        .add(Restrictions.eq("companyName", company))
			        .add(Restrictions.eq("plant", plant))).size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Employee> getSameCompanyPlantEmployeeList(String company,
				String plant, int from, int to) throws Exception {
			getHibernateTemplate().setCacheQueries(true);  
			return (List<Employee>) getHibernateTemplate().findByCriteria(
			        DetachedCriteria.forClass(Employee.class)
			        .add(Restrictions.eq("companyName", company))
			        .add(Restrictions.eq("plant", plant))).subList(from, to);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<CompanyMaster> getAllCompanies() throws Exception {
		
			return (List<CompanyMaster>)getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(CompanyMaster.class));
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getCompanybyID(String id) throws Exception {
			
			Object[] params = {id};
			String companyName = ((List<String>) getHibernateTemplate().find("select cm.company_name from CompanyMaster cm where cm.company_id = ?",params)).get(0);
			return companyName;
		
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getUserLogin(String userName, String password) throws Exception {
			
			Object[] params = {userName , password};
			
			String id= ((List<String>)  getHibernateTemplate().find("select eli.user_id from EmpLoginInfo eli where eli.userName = ? and eli.password = ? ",params)).get(0);
			
			
			return id;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<ApplicationSettings> getAllRoles() throws Exception {
			
			List<ApplicationSettings> allroles = (List<ApplicationSettings>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ApplicationSettings.class)
			        .add(Restrictions.eq("codeType",USERROLE)));
			
			return allroles;
		
			
		}
		
		
		public List<PlantMaster> getPlantsbycompanyId(String companyID)
		{
			@SuppressWarnings("unchecked")
			List<PlantMaster> plantName = (List<PlantMaster>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(PlantMaster.class)
			        						.add(Restrictions.eq("company_id",companyID)));
			
			return plantName;
		}
		
		public List<PlantMaster>getAllPlants()
		{
			@SuppressWarnings("unchecked")
			List<PlantMaster> plantName = (List<PlantMaster>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(PlantMaster.class));			
			return plantName;
		}

		@Override
		public List<DesignationMaster> getAllDesignations() {
			
			@SuppressWarnings("unchecked")
			List<DesignationMaster> designationName = (List<DesignationMaster>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(DesignationMaster.class));			
			return designationName;
		}

		@Override
		public List<DepartmentMaster> getAllDepartments() {
			
			@SuppressWarnings("unchecked")
			List<DepartmentMaster> departmentName = (List<DepartmentMaster>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(DepartmentMaster.class));			
			return departmentName;
		}
		
		public int getLatestSerial_no(Employee emp){
			
			Integer returnValue = 0;
			returnValue= (Integer)getHibernateTemplate().find("select MAX(serial_no) from Employee").get(0);
			return returnValue;
			
			
		}
		
		public int getLatestSerial_no(EmpLoginInfo emp){
			
			Integer returnValue = 0;
			returnValue= (Integer)getHibernateTemplate().find("select MAX(serial_no) from EmpLoginInfo").get(0);
			return returnValue;
			
		}
		
		public boolean saveOrUpdateEmployee(EmpLoginInfo employee) throws Exception{
			
			getHibernateTemplate().save(employee);
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String checkExistingUser(String loginID) throws Exception {
			
			String returnValue;
			List<Employee> queryValue = new ArrayList<Employee>();
			getHibernateTemplate().setCacheQueries(true);  
			queryValue = (List<Employee>) getHibernateTemplate().findByCriteria(
			        DetachedCriteria.forClass(Employee.class)
			        .add(Restrictions.eq("emailID", loginID)));
			        
			if(queryValue==null || queryValue.size()==0)
			{
				returnValue = USER_NOT_EXISTS;
			}
			else
				returnValue = USER_EXISTS;
			
			
			return returnValue;
		}
		
		
}
