package com.vgil.model;

import java.util.Date;

public class TrackChallenge implements java.io.Serializable {
	/**
	 * 
	 */			
	private static final long serialVersionUID = 1L;
	
	private long id;
	private int challengeNo;
	private String challenge;
	private String userId;
	private int persuePoints;
	private int ideaGenerationPoints; 
	private int prototypePoints;
	private int scaleupPoints;
	private int totalPoints;
	private int points;
	private Date startDate;
	private Date endDate;
	private String status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getChallengeNo() {
		return challengeNo;
	}
	public void setChallengeNo(int challengeNo) {
		this.challengeNo = challengeNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPersuePoints() {
		return persuePoints;
	}
	public void setPersuePoints(int persuePoints) {
		this.persuePoints = persuePoints;
	}
	public int getIdeaGenerationPoints() {
		return ideaGenerationPoints;
	}
	public void setIdeaGenerationPoints(int ideaGenerationPoints) {
		this.ideaGenerationPoints = ideaGenerationPoints;
	}
	public int getPrototypePoints() {
		return prototypePoints;
	}
	public void setPrototypePoints(int prototypePoints) {
		this.prototypePoints = prototypePoints;
	}
	public int getScaleupPoints() {
		return scaleupPoints;
	}
	public void setScaleupPoints(int scaleupPoints) {
		this.scaleupPoints = scaleupPoints;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPoints() {
		return points;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public String getChallenge() {
		return challenge;
	}
}
