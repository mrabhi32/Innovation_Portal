package com.vgil.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vgil.dao.MailDAO;
import com.vgil.model.VGILMail;


public class MailDAOImpl extends HibernateDaoSupport implements MailDAO{

	@Override
	public VGILMail getDBConfigureCustomerEmail(long emailId) {
		return getHibernateTemplate().load(VGILMail.class, emailId);
	}
 
	
}
