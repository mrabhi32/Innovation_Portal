package com.vgil.model;

public class PersonalTrackerVO {

	int challengesPursued;
	int ideaGenerated;
	int ideaProtyping;
	int ideaImplemented;
	String challengesPursuedString;
	String ideaGeneratedString;

	String ideaProtypingString;
	String ideaImplementedString;
	String savingPotential;
	String savingActualized;
	
	public int getChallengesPursued() {
		return challengesPursued;
	}
	public void setChallengesPursued(int challengesPursued) {
		this.challengesPursued = challengesPursued;
	}
	public int getIdeaGenerated() {
		return ideaGenerated;
	}
	public void setIdeaGenerated(int ideaGenerated) {
		this.ideaGenerated = ideaGenerated;
	}
	public int getIdeaProtyping() {
		return ideaProtyping;
	}
	public void setIdeaProtyping(int ideaProtyping) {
		this.ideaProtyping = ideaProtyping;
	}
	public int getIdeaImplemented() {
		return ideaImplemented;
	}
	public void setIdeaImplemented(int ideaImplemented) {
		this.ideaImplemented = ideaImplemented;
	}
	public String getSavingPotential() {
		return savingPotential;
	}
	public void setSavingPotential(String potential) {
		this.savingPotential = potential;
	}
	public String getSavingActualized() {
		return savingActualized;
	}
	public void setSavingActualized(String savingActualized) {
		this.savingActualized = savingActualized;
	}
	public String getChallengesPursuedString() {
		return get3DigitCount(challengesPursued);
	}
	public void setChallengesPursuedString(String challengesPursuedString) {
		this.challengesPursuedString = challengesPursuedString;
	}
	public String getIdeaGeneratedString() {
		return get3DigitCount(ideaGenerated);
	}
	public void setIdeaGeneratedString(String ideaGeneratedString) {
		this.ideaGeneratedString = ideaGeneratedString;
	}
	public String getIdeaProtypingString() {
		return get3DigitCount(ideaProtyping);
	}
	public void setIdeaProtypingString(String ideaProtypingString) {
		this.ideaProtypingString = ideaProtypingString;
	}
	public String getIdeaImplementedString() {
		return get3DigitCount(ideaImplemented);
	}
	public void setIdeaImplementedString(String ideaImplementedString) {
		this.ideaImplementedString = ideaImplementedString;
	}
	public String get3DigitCount(int count){
		String number = String.valueOf(count);
		if(number!=null){
			while(number.length()<3){
				number="0"+number;
			}
			if(number.length()>3){
				number = number.substring(0, 3);
			}
		}
		return number;
	}
}
