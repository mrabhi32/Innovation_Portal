package com.vgil.model;

import java.sql.Timestamp;
import java.util.Date;

public class ChallengePoints {
	/**
	 * 
	 */			

	private int serial_no;
	private String Idea_id;
	private String user_id;
	private String challenge_id;
	private int persue_points;
	private int idea_generation_points;
	private int prototype_points;
	private int scaleup_points;
	private int total_points;
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
		return Idea_id;
	}
	public void setIdea_id(String idea_id) {
		Idea_id = idea_id;
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
	public int getPersue_points() {
		return persue_points;
	}
	public void setPersue_points(int persue_points) {
		this.persue_points = persue_points;
	}
	public int getIdea_generation_points() {
		return idea_generation_points;
	}
	public void setIdea_generation_points(int idea_generation_points) {
		this.idea_generation_points = idea_generation_points;
	}
	public int getPrototype_points() {
		return prototype_points;
	}
	public void setPrototype_points(int prototype_points) {
		this.prototype_points = prototype_points;
	}
	public int getScaleup_points() {
		return scaleup_points;
	}
	public void setScaleup_points(int scaleup_points) {
		this.scaleup_points = scaleup_points;
	}
	public int getTotal_points() {
		return total_points;
	}
	public void setTotal_points(int total_points) {
		this.total_points = total_points;
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
