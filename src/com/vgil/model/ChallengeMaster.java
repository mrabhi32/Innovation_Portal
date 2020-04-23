package com.vgil.model;

import java.sql.Timestamp;


public class ChallengeMaster extends Object  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int serial_no;
	private String challenge_id;
	private String company_id;
	private String company_name;
	private String plant_id;
	private String plant_name;
	private String challenge_type;
	private String challenge_category;
	private String priority;
	private String challenge;
	private String quant_meas_success;
	private Timestamp challenge_start_date;
	private Timestamp challenge_end_date;
	private String challenge_status;
	private String reason_for_drop;
	private Timestamp created_date;
	private String created_by;
	private Timestamp last_updated_date;
	private String last_updated_by;
	private String company_plant;
	
	public int getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}
	public String getChallenge_id() {
		return challenge_id;
	}
	public String getCompany_plant() {
		return company_plant;
	}
	public void setCompany_plant(String company_plant) {
		this.company_plant = company_plant;
	}
	public void setChallenge_id(String challenge_id) {
		this.challenge_id = challenge_id;
	}
	
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPlant_name() {
		return plant_name;
	}
	public void setPlant_name(String plant_name) {
		this.plant_name = plant_name;
	}
	public String getPlant_id() {
		return plant_id;
	}
	public void setPlant_id(String plant_id) {
		this.plant_id = plant_id;
	}
	public String getChallenge_type() {
		return challenge_type;
	}
	public void setChallenge_type(String challenge_type) {
		this.challenge_type = challenge_type;
	}
	public String getChallenge_category() {
		return challenge_category;
	}
	public void setChallenge_category(String challenge_category) {
		this.challenge_category = challenge_category;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public String getQuant_meas_success() {
		return quant_meas_success;
	}
	public void setQuant_meas_success(String quant_meas_success) {
		this.quant_meas_success = quant_meas_success;
	}
	public Timestamp getChallenge_start_date() {
		return challenge_start_date;
	}
	public void setChallenge_start_date(Timestamp challenge_start_date) {
		this.challenge_start_date = challenge_start_date;
	}
	public Timestamp getChallenge_end_date() {
		return challenge_end_date;
	}
	public void setChallenge_end_date(Timestamp challenge_end_date) {
		this.challenge_end_date = challenge_end_date;
	}
	public String getChallenge_status() {
		return challenge_status;
	}
	public void setChallenge_status(String challenge_status) {
		this.challenge_status = challenge_status;
	}
	public String getReason_for_drop() {
		return reason_for_drop;
	}
	public void setReason_for_drop(String reason_for_drop) {
		this.reason_for_drop = reason_for_drop;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}
