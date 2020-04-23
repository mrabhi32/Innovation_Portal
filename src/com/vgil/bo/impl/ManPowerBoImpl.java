package com.vgil.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vgil.bo.ManPowerBO;
import com.vgil.dao.impl.ManPowerDAOImpl;
import com.vgil.model.Employee;
import com.vgil.model.Manpower;

public class ManPowerBoImpl implements ManPowerBO {
    
	//@Autowired
	ManPowerDAOImpl manPowerDAO;

	public ManPowerDAOImpl getManPowerDAO() {
		return manPowerDAO;
	}

	public void setManPowerDAO(ManPowerDAOImpl manPowerDAO) {
		this.manPowerDAO = manPowerDAO;
	}

	@Override
	public void saveOrUpdateManPower(Manpower manPower) {
		manPowerDAO.saveOrUpdateManpower(manPower);
	}

	@Override
	public List<Employee> getManPowerData() {
		List<Employee> responseList;
		responseList = manPowerDAO.getManPowerData();
		return responseList;
	}

	
}
