package com.vgil.model;

import java.sql.Timestamp;

public class ChallengeTeam implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int serial_no;
	private String team_id;
	private String user_id;
	private String challenge_id;
	private String challenge_status;
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
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
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
	public String getChallenge_status() {
		return challenge_status;
	}
	public void setChallenge_status(String challenge_status) {
		this.challenge_status = challenge_status;
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
