package com.vgil.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.core.action.login.LoginAction;
 
public class AdminAction extends LoginAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminAction.class);
	List<String> roles;
	List<String> toolbar;
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}	
	
}