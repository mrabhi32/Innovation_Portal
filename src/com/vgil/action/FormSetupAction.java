package com.vgil.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.core.action.BaseAction;
import com.vgil.bo.ChallengeBankBO;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.CompanyMaster;

import com.vgil.model.Employee;
import com.vgil.model.PlantMaster;

import static com.vgil.model.ApplicationConstants.*;

public class FormSetupAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	String message;
    List<String> companyNames = new ArrayList<String>();           
	List<String> companyPlants = new ArrayList<String>();           
	Map<String,List<String>> plantNames; 
	List<String> companyPlant = new ArrayList<String>();
	List<String> categoryList = new ArrayList<String>();
	List<String> priorityList = new ArrayList<String>();
	private int serial_no;
	private String challenge_id;

	ChallengeMaster challenge;
	
	public ChallengeMaster getChallenge() {
		return challenge;
	}






	public void setChallenge(ChallengeMaster challenge) {
		this.challenge = challenge;
	}






	public List<String> getPriorityList() {
		return priorityList;
	}






	public void setPriorityList(List<String> priorityList) {
		this.priorityList = priorityList;
	}

	List<String> challengeTypeList = new ArrayList<String>();
	List<ApplicationSettings> applicationTable;
	ChallengeBankBO challengeBankBO;
	
	

	@SuppressWarnings("unchecked")
	public String SetUpChallengeForm(){

		//Gets the Company and Plant combination from the Database
		
		System.out.println(getSession().get("UserAccesibleCompany"));
		companyNames = (List<String>) getSession().get("UserAccesibleCompany");            // Gets the List of User Accesible companies from the Session
		plantNames = (Map<String,List<String>>) getSession().get("UserAccesiblePlants");   // Gets the User Accesible Plants from the Session
		for(int compiter=0; compiter<companyNames.size(); compiter++) 
		{
			companyPlants = plantNames.get(companyNames.get(compiter));
			for(int plantiter=0; plantiter<companyPlants.size(); plantiter++)
			{
				String Combintation = companyNames.get(compiter)+COMPANYPLANTSEPERATOR+companyPlants.get(plantiter);
				companyPlant.add(Combintation);
			}
		}		
		
		
		// Gets the Category Values from the database
		
		applicationTable = challengeBankBO.getApplicationSettings();
		for(int iter=0; iter<applicationTable.size(); iter++ )
		{
			
			if(applicationTable.get(iter).getCodeType().equals(CATEGORY)){
				
				categoryList.add(applicationTable.get(iter).getValue());
			}
			
			else if(applicationTable.get(iter).getCodeType().equals(CHALLENGETYPE))
			{
				challengeTypeList.add(applicationTable.get(iter).getValue());
			}
			
			else if(applicationTable.get(iter).getCodeType().equals(PRIORITY))
			{
				priorityList.add(applicationTable.get(iter).getValue());
			}
			
		}
		
		getSession().put(CATEGORYLIST, categoryList);
		getSession().put(CHALLENGETYPELIST, challengeTypeList);
		getSession().put(PRIORITYLIST, priorityList);
		
		
	
		return SUCCESS;
	}

	
@SuppressWarnings("unchecked")
public String submitChallenge(){
		
	
	
	    Employee loggedInUser = (Employee)getSession().get("user");
		String expression = "^[0-9]*$";
	    Pattern pattern = Pattern.compile(expression);
	    Matcher matcher; 
	    List<CompanyMaster> companyMaster = new ArrayList<CompanyMaster>();
		List<PlantMaster> plantMaster = new ArrayList<PlantMaster>();
		companyMaster = (List<CompanyMaster>)getSession().get("MasterCompanyData");
		plantMaster = (List<PlantMaster>)getSession().get("MasterPlantData");
		

	    
		clearErrorsAndMessages();
		try{
			
		
		if(challenge.getChallenge()==null)
		{
		
				addActionError("Challenge Description cannot be Null");
		
		}
		
		matcher = pattern.matcher(challenge.getQuant_meas_success());
		
		if(!matcher.matches())
		{
			addActionError("Quantitative Measure of Success Should be only number.Please try again");			
			
		}
			

		
		serial_no = challengeBankBO.getLastSerialNumber();
		challenge_id = CHALLENGECONSTANT+(serial_no+1);
		challenge.setSerial_no(serial_no+1);
		challenge.setChallenge_id(challenge_id);
		String companyName = challenge.getCompany_plant().split(COMPANYPLANTSEPERATOR)[0];
		String plantName   = challenge.getCompany_plant().split(COMPANYPLANTSEPERATOR)[1];
		
		for(int i=0;i<companyMaster.size();i++)
		{
			if(companyMaster.get(i).getCompany_name().equals(companyName))
			{
				challenge.setCompany_id(companyMaster.get(i).getCompany_id());
			}
		}
		
		
		
		
		for(int i=0;i<plantMaster.size();i++)
		{
			if(plantMaster.get(i).getPlant_name().equals(plantName))
			{
				challenge.setPlant_id(plantMaster.get(i).getPlant_id());
			}
		}
		
		
		challenge.setChallenge_start_date(new Timestamp(System.currentTimeMillis()));
		challenge.setReason_for_drop(null);
		challenge.setChallenge_status(CHALLENGEOPEN);
		challenge.setCreated_date(new Timestamp(System.currentTimeMillis()));
		challenge.setCreated_by(loggedInUser.getUser_id());
		challenge.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
		challenge.setLast_updated_by(loggedInUser.getUser_id());
		
		challengeBankBO.createChallenge(challenge);
		addActionMessage("Challenge Created Successfully.");
		
		
		} catch(Exception e){
				
			e.printStackTrace();
				message = "Challenge not saved.Please try again.";
				addActionError("Challenge not saved. Reason :"+e.getMessage()+". Please try again.");
			}
		
		return "error";
	}
	


	public List<String> getChallengeTypeList() {
		return challengeTypeList;
	}
	public void setChallengeTypeList(List<String> challengeTypeList) {
		this.challengeTypeList = challengeTypeList;
	}
	public ChallengeBankBO getChallengeBankBO() {
		return challengeBankBO;
	}

	public void setChallengeBankBO(ChallengeBankBO challengeBankBO) {
		this.challengeBankBO = challengeBankBO;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
		
	public List<String> getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(List<String> companyNames) {
		this.companyNames = companyNames;
	}

	public List<String> getCompanyPlants() {
		return companyPlants;
	}

	public void setCompanyPlants(List<String> companyPlants) {
		this.companyPlants = companyPlants;
	}

	public Map<String, List<String>> getPlantNames() {
		return plantNames;
	}

	public void setPlantNames(Map<String, List<String>> plantNames) {
		this.plantNames = plantNames;
	}

	public List<String> getCompanyPlant() {
		return companyPlant;
	}

	public void setCompanyPlant(List<String> companyPlant) {
		this.companyPlant = companyPlant;
	}

	@Override
	public void validate() {
	}
}
