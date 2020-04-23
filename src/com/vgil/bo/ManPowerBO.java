package com.vgil.bo;

import java.util.List;

import com.vgil.model.Employee;
import com.vgil.model.Manpower;

public interface ManPowerBO {

	void saveOrUpdateManPower(Manpower manPower);

//	/TreeMap<String, Object> getEmployeeById(ManpowerVO emp);
	public List<Employee> getManPowerData();

	
}
