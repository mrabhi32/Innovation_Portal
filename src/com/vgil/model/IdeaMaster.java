package com.vgil.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdeaMaster {
	/**
	 * 
	 */
	private int serial_no;
	private String idea_id;
	private String user_id;
	private String challenge_id;
	private String frame;
	private String idea;
	private String document_uploaded;
	private String impact_value;
	private String investment_classification;
	private String investment_value;
	private int implementation_time;
	private String impact_level;
	private String unknowns;
	private String idea_priority;
	private String idea_status;
	private Timestamp created_date;
	private String created_by;
	private Timestamp last_updated_date;
	private String last_updated_by;
	
	
	public int getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}
	public String getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(String idea_id) {
		this.idea_id = idea_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getChallenge_id() {
		return challenge_id;
	}
	public void setChallenge_id(String challenge_id) {
		this.challenge_id = challenge_id;
	}
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public String getDocument_uploaded() {
		return document_uploaded;
	}
	public void setDocument_uploaded(String document_uploaded) {
		this.document_uploaded = document_uploaded;
	}
	public String getImpact_value() {
		return impact_value;
	}
	public void setImpact_value(String impact_value) {
		this.impact_value = impact_value;
	}
	public String getInvestment_classification() {
		return investment_classification;
	}
	public void setInvestment_classification(String investment_classification) {
		this.investment_classification = investment_classification;
	}
	public String getInvestment_value() {
		return investment_value;
	}
	public void setInvestment_value(String investment_value) {
		this.investment_value = investment_value;
	}
	public int getImplementation_time() {
		return implementation_time;
	}
	public void setImplementation_time(int implementation_time) {
		this.implementation_time = implementation_time;
	}
	public String getImpact_level() {
		return impact_level;
	}
	public void setImpact_level(String impact_level) {
		this.impact_level = impact_level;
	}
	public String getUnknowns() {
		return unknowns;
	}
	public void setUnknowns(String unknowns) {
		this.unknowns = unknowns;
	}
	public String getIdea_priority() {
		return idea_priority;
	}
	public void setIdea_priority(String idea_priority) {
		this.idea_priority = idea_priority;
	}
	public String getIdea_status() {
		return idea_status;
	}
	public void setIdea_status(String idea_status) {
		this.idea_status = idea_status;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Timestamp getLast_updated_date() {
		return last_updated_date;
	}
	public void setLast_updated_date(Timestamp last_updated_date) {
		this.last_updated_date = last_updated_date;
	}
	public String getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	

}
