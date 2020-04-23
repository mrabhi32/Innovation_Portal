package com.vgil.model;

public class ChallengeBank implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String dateSubmitted;
	private String company;
	private String plant;
	private String challenge;
	private String challengeType;
	private String category;
	private int potentialRsMil;
	private String status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public String getChallengeType() {
		return challengeType;
	}
	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPotentialRsMil() {
		return potentialRsMil;
	}
	public void setPotentialRsMil(int potentialRsMil) {
		this.potentialRsMil = potentialRsMil;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
