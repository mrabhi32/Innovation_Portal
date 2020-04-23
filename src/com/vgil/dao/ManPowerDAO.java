package com.vgil.dao;

import java.util.List;

import com.vgil.model.Employee;
import com.vgil.model.Manpower;

public interface ManPowerDAO{
	
	public List<Employee> getManPowerData();
	
	public boolean saveOrUpdateManpower(Manpower manPower);
	
}