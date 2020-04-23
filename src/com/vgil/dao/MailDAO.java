package com.vgil.dao;

import com.vgil.model.VGILMail;

public interface MailDAO{
	
	VGILMail getDBConfigureCustomerEmail(long emailId);
}