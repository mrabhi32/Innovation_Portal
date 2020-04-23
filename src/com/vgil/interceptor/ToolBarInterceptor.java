package com.vgil.interceptor;
/*
 * Copyright (c) 2011 Sears Holdings Corp.
 * 3333 Beverly Rd, Hoffman Estates, Illinois, 60179, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Sears Holdings Corp. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms software development.
 */
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import com.vgil.model.Employee;

/**
 * This custom intercepter class is fired before every action, to check on validity of session. 
 * At this time "Login" method is excluded from check as we will never have a session as 
 * we fire up the initial Login.jsp
 * <br>
 * @author sbijwe
 * @date 
 */
public class ToolBarInterceptor extends MethodFilterInterceptor {


	
	/**
	 * Added as default just to remove the warning
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	public void init() {
		
	}

	/**
	 * Only method that excludes the invocation of the session check in doIntercept
	 * is "Login" as declared in struts.xml
	 * 
	 * @param ActionInvocation invocation 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		//If method is not filtered out then intercept
		HttpServletResponse res = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
        res.setHeader("CACHE-CONTROL", "no-cache, no-store, must-revalidate");
        res.addHeader("Pragma", "no-cache");
        res.addHeader("Expires", "0");


		if (applyInterceptor(invocation)){
			return doIntercept(invocation);
		}
		//Move on Happy Path
		else {
			return invocation.invoke();
		}
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//ServletActionContext ac = (ServletActionContext)invocation.getInvocationContext();
		
		Map session = ActionContext.getContext().getSession();
		//Use Value Stack to display message from Interceptor to the view		
		
		ValueStack vs = invocation.getStack();	

				if(ServletActionContext.getRequest().getParameter("activeToolBar") != null)
			{	
					Employee  employee = (Employee) ServletActionContext.getRequest().getSession().getAttribute("user");
					employee.getUser().getUserSettings().setActiveToolBar(ServletActionContext.getRequest().getParameter("activeToolBar"));
			}	
		//}
		return invocation.invoke();
	}


}
