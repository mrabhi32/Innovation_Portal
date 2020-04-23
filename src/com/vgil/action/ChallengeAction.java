package com.vgil.action;

import static com.vgil.model.ApplicationConstants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.apache.log4j.Logger;
import com.core.action.BaseAction;
import com.vgil.bo.ChallengeBankBO;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.CompanyMaster;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.PlantMaster;
 
public class ChallengeAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ChallengeAction.class);
	
	
	private ChallengeBankBO challengeBankBO;
	private ChallengeMaster challenge;
	private IdeaMaster ideaBank;
	private String userIds;
	private List<String> selectChallenge= new ArrayList<String>();
	private String challengeID;
	private int selectIndex;

	public IdeaMaster getIdeaBank() {
		return ideaBank;
	}

	public void setIdeaBank(IdeaMaster ideaBank) {
		this.ideaBank = ideaBank;
	}




	public String getChallengeID() {
		return challengeID;
	}

	public void setChallengeID(String challengeID) {
		this.challengeID = challengeID;
	}

	public ChallengeMaster getChallenge() {
		return challenge;
	}

	public void setChallenge(ChallengeMaster challenge) {
		this.challenge = challenge;
	}

	public void setChallengeBankBO(ChallengeBankBO challengeBankBO) {
		this.challengeBankBO = challengeBankBO;
	}

	public ChallengeBankBO getChallengeBankBO() {
		return challengeBankBO;
	}	
	
	public String saveAsCurrentSelectedChallenge(){
		try {
		/*	if(challengeID != 0){
				getSession().put("currentChallengeNo", challengeID);
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	public String getChallengesForTeamSelection(){
		Employee employee = ((Employee)(getSession().get("user")));
		try {
			selectChallenge = new ArrayList<String>();
			List<ChallengeMaster> challengesList = challengeBankBO.getPursuedChallengeList(employee.getUser_id());
			
			for (Iterator<ChallengeMaster> iterator = challengesList.iterator(); iterator.hasNext();) {
				ChallengeMaster challenge = (ChallengeMaster) iterator.next();
				selectChallenge.add(challenge.getChallenge_id());
			}
			getSession().put("myChallenges", challengesList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String populateChallenge(){
		
		@SuppressWarnings("unchecked")
		List<ChallengeMaster> challenges = ((List<ChallengeMaster>)(getSession().get("myChallenges")));
		try {
			selectChallenge = new ArrayList<String>();
			for (Iterator<ChallengeMaster> iterator = challenges.iterator(); iterator.hasNext();) {
				ChallengeMaster challenge = (ChallengeMaster) iterator.next();
				selectChallenge.add(challenge.getChallenge_id());
				if(challengeID == challenge.getChallenge_id()){
					this.challenge = challenge;
					selectIndex = selectChallenge.size(); 
				}
			}
			getSession().put("currentChallengeNo", challengeID);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
	public String saveIdeaForChallenge(){
		try {
			
			System.out.println("Reached save Idea for Challenge");
			selectChallenge = new ArrayList<String>();
			String ideaChallengeNo = challengeID;
			try {
				ideaChallengeNo = (String) getSession().get("currentChallengeNo");
				challengeID = ideaChallengeNo;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			ideaBank.setChallenge_id(ideaChallengeNo);
			challengeBankBO.saveOrUpdateIdea(ideaBank);
			@SuppressWarnings("unchecked")
			List<ChallengeMaster> challengesList = ((List<ChallengeMaster>)(getSession().get("myChallenges")));
			for (Iterator<ChallengeMaster> iterator = challengesList.iterator(); iterator.hasNext();) {
				ChallengeMaster challenge = (ChallengeMaster) iterator.next();
				selectChallenge.add(challenge.getChallenge_id());
			}
			addActionMessage("Idea Added Successfuly.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//return ERROR;
			addActionError("Idea Creation failed.Please try again latter.");
		}
		return populateChallenge();
	}
	
	
	@SuppressWarnings("unchecked")
	public String uploadChallenges(){
		
		ChallengeMaster challenge;
		String[] ChallengeString;
		FileReader fileReader;
		BufferedReader bufferedReader;
		String errorString;
		String returnValue = ERROR;
		String strLine;
		int lineCount = 0;
		List<CompanyMaster> companyMaster = new ArrayList<CompanyMaster>();
		List<PlantMaster> plantMaster = new ArrayList<PlantMaster>();
		File uploadedFile = getFile();
		try {
			
			
			
			Employee loggedInEmployee = ((Employee)(getSession().get("user")));
		    
			
			errorString = challengeBankBO.validateChallengeEntries(uploadedFile,getSession()); 
		    
			fileReader = new FileReader(uploadedFile);
			bufferedReader = new BufferedReader(fileReader);

		    
		    if(errorString==VALIDATION_PASS)
			{
		    	
		    	while ((strLine = bufferedReader.readLine()) != null) 
		    	{

					//To Read the file from Line 2, since Line 1 will contain the headers.
					if(lineCount!=0)
					{
					
						ChallengeString = strLine.split(",");
						challenge = new ChallengeMaster();
						
						int serial_no = challengeBankBO.getLastSerialNumber();
						
						challenge.setSerial_no(serial_no+1);
						challenge.setChallenge_id(CHALLENGECONSTANT+(serial_no+1));
						
						companyMaster = (List<CompanyMaster>)session.get("MasterCompanyData");
						plantMaster = (List<PlantMaster>)session.get("MasterPlantData");
						
						for(int i=0;i<companyMaster.size();i++)
						{
							if(companyMaster.get(i).getCompany_name().equalsIgnoreCase(ChallengeString[0].trim()))
							{
								challenge.setCompany_id(companyMaster.get(i).getCompany_id());
							}
						}



						for(int i=0;i<plantMaster.size();i++)
						{
							if(plantMaster.get(i).getPlant_name().equalsIgnoreCase(ChallengeString[1].trim()))
							{
								challenge.setPlant_id(plantMaster.get(i).getPlant_id());
							}
						}
						
						
						challenge.setChallenge_type(ChallengeString[2].trim());
						challenge.setChallenge_category(ChallengeString[3].trim());
						challenge.setPriority(ChallengeString[4].trim());
						challenge.setChallenge(ChallengeString[5].trim());
						challenge.setQuant_meas_success(ChallengeString[6].trim());
						challenge.setChallenge_start_date(new Timestamp(System.currentTimeMillis()));
						challenge.setChallenge_end_date(null);
						challenge.setChallenge_status(CHALLENGEOPEN);
						challenge.setReason_for_drop(NOTAPPLICABLE);
						
						challenge.setCreated_date(new Timestamp(System.currentTimeMillis()));
						challenge.setCreated_by(loggedInEmployee.getUser_id());
						challenge.setLast_updated_date(new Timestamp(System.currentTimeMillis()));
						challenge.setLast_updated_by(loggedInEmployee.getUser_id());
						
						challengeBankBO.createChallenge(challenge);
						returnValue = SUCCESS;
					}
					lineCount++;
		    	}
			}
		    
		    else if (errorString.equalsIgnoreCase(BLANKSPACE))
		    {
		    	addActionError("The Uploaded file is Empty. Please add a valid file.");
		    	returnValue = ERROR;
		    }
		    else 
			{
		    	getSession().put("ErrorMessage", errorString);
		    	if(errorString .equals("Upload file does not have any record. Please upload a file with proper Records."))
		    	{
		    		addActionError("File Upload Not Successful. File is Empty. Plese upload a file with Proper details.");
		    	}
		    	else
		    	{
		    		addActionError("File Upload Not Successful. Please click on the Error Log link to see the details.");	
		    	}
		    	
				returnValue = ERROR;
			}
		    
		    
		    
		} catch (Exception e) {
			
			
			logger.error(e.getMessage(),e);
			addActionError(e.getMessage());
			return ERROR;
		}
		
		
		if(returnValue.equalsIgnoreCase(SUCCESS))
		{
			addActionMessage("Challenges uploaded successfully");	
		}
	
		
		try {
			bufferedReader.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	

	public String createTeamForChallenge(){
		@SuppressWarnings("unchecked")
		List<ChallengeMaster> challengesList = ((List<ChallengeMaster>)(getSession().get("myChallenges")));
		int challengeId =  (Integer)getSession().get("currentChallengeNo");
		for (Iterator<ChallengeMaster> iterator = challengesList.iterator(); iterator.hasNext();) {
			ChallengeMaster challenge = (ChallengeMaster) iterator.next();
			if(challengeId == Integer.parseInt(challenge.getChallenge_id())){
				this.challenge = challenge;
				break;
			}
		}
		List <String> userIdList = new ArrayList<String>();
		String [] users = userIds.split(",");
		for(int i = 0; i< users.length;i++){
			userIdList.add(users[i]);
		}
		challengeBankBO.processChallengeTrackingForTeam(userIdList, (Integer.parseInt(challenge.getChallenge_id())),challenge.getChallenge() ,challenge.getChallenge_type(),challenge.getChallenge_status());
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

	public void setSelectChallenge(List<String> selectChallenge) {
		this.selectChallenge = selectChallenge;
	}

	public List<String> getSelectChallenge() {
		return selectChallenge;
	}

	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}

	public int getSelectIndex() {
		return selectIndex;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
}