package com.vgil.model;

public class PersonalChallengeRewardStatusVO {

	int challengeNo;
	String challengeType;
	long challengePersuedPoints;
	long ideaIdeaGenerationPoints;
	long protoypeCompletePoints;
	long ideaScaleUpPoints;
	long totalPointsInBank;
	long pointsReleased;
	long pointsBalancedInBank;
	
	public int getChallengeNo() {
		return challengeNo;
	}
	public void setChallengeNo(int challengeNo) {
		this.challengeNo = challengeNo;
	}
	public String getChallengeType() {
		return challengeType;
	}
	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}
	public long getChallengePersuedPoints() {
		return challengePersuedPoints;
	}
	public void setChallengePersuedPoints(long challengePersuedPoints) {
		this.challengePersuedPoints = challengePersuedPoints;
	}
	public long getIdeaIdeaGenerationPoints() {
		return ideaIdeaGenerationPoints;
	}
	public void setIdeaIdeaGenerationPoints(long ideaIdeaGenerationPoints) {
		this.ideaIdeaGenerationPoints = ideaIdeaGenerationPoints;
	}
	public long getProtoypeCompletePoints() {
		return protoypeCompletePoints;
	}
	public void setProtoypeCompletePoints(long protoypeCompletePoints) {
		this.protoypeCompletePoints = protoypeCompletePoints;
	}
	public long getIdeaScaleUpPoints() {
		return ideaScaleUpPoints;
	}
	public void setIdeaScaleUpPoints(long ideaScaleUpPoints) {
		this.ideaScaleUpPoints = ideaScaleUpPoints;
	}
	public long getTotalPointsInBank() {
		return totalPointsInBank;
	}
	public void setTotalPointsInBank(long totalPointsInBank) {
		this.totalPointsInBank = totalPointsInBank;
	}
	public long getPointsReleased() {
		return pointsReleased;
	}
	public void setPointsReleased(long pointsReleased) {
		this.pointsReleased = pointsReleased;
	}
	public long getPointsBalancedInBank() {
		return pointsBalancedInBank;
	}
	public void setPointsBalancedInBank(long pointsBalancedInBank) {
		this.pointsBalancedInBank = pointsBalancedInBank;
	}
}
