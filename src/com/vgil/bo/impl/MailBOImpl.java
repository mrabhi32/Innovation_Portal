package com.vgil.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.vgil.bo.MailBO;
import com.vgil.dao.MailDAO;
import com.vgil.model.VGILMail;

public class MailBOImpl implements MailBO {
    
	//@Autowired
	MailDAO mailDao;

	public MailDAO getMailDao() {
		return mailDao;
	}

	public void setMailDao(MailDAO mailDao) {
		this.mailDao = mailDao;
	}

	@Override
	public VGILMail getCustomerConfigureEmail(long emailId) {
		return mailDao.getDBConfigureCustomerEmail(emailId);
	}
 
}
