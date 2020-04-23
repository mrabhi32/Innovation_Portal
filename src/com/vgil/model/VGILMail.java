package com.vgil.model;

public class VGILMail {
	private Long id;
	private String subject;
	private String body;
	private String desc;
	private String to;
	private String fromManager;
	private String fromAdmin;

	public String getFromManager() {
		return fromManager;
	}
	public void setFromManager(String fromManager) {
		this.fromManager = fromManager;
	}
	
	public String getFromAdmin() {
		return fromAdmin;
	}
	public void setFromAdmin(String fromAdmin) {
		this.fromAdmin = fromAdmin;
	}
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
