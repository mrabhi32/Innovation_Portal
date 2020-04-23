package com.vgil.bo;
import com.vgil.model.VGILMail;
public interface MailBO {
	VGILMail getCustomerConfigureEmail(long customerId);
}
