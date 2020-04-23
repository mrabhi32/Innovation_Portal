package com.vgil.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vgil.dao.ManPowerDAO;
import com.vgil.model.Employee;
import com.vgil.model.Manpower;


public class ManPowerDAOImpl extends HibernateDaoSupport implements ManPowerDAO{

	@Override
	public List<Employee> getManPowerData() {
		getHibernateTemplate().setCacheQueries(true);  
		List<Employee> objList = new ArrayList<Employee>();
		objList = getHibernateTemplate().loadAll(Employee.class);
		return objList;
	}
	
	

	@Override
	public boolean saveOrUpdateManpower(Manpower manPower) {
		// TODO Auto-generated method stub
		return false;
	}


//	@Override
/*	public boolean saveOrUpdateManpower(ManpowerVO manPower) {
		if(manPower.getCompany() != null && !"".equals(manPower.getCompany().trim())){
			Employee tempManPower = getManPowerData(manPower.getCompany());
			tempManPower.setCompany(manPower.getCompany());
			tempManPower.setPlant(manPower.getPlant());
			tempManPower.setManpowerCount(manPower.getManpowerCount());
			tempManPower.setFs1(manPower.getFs1());
			tempManPower.setFs2(manPower.getFs2());
			tempManPower.setFs3(manPower.getFs3());
			tempManPower.setInnovators(manPower.getInnovators());
			tempManPower.setInnovattionStarter(manPower.getInnovattionStarter());
			tempManPower.setBuddies(manPower.getBuddies());
			getHibernateTemplate().saveOrUpdate(tempManPower);
		}else {
			getHibernateTemplate().saveOrUpdate(manPower);
		}
		return true;
	}*/
	
}
