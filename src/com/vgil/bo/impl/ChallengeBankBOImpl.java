package com.vgil.bo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.core.action.BaseAction;
import com.core.bo.ApplicationSettingsBo;
import com.vgil.bo.ChallengeBankBO;
import com.vgil.dao.ChallengeBankDAO;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.PersonalTrackerVO;
import com.vgil.model.TeamMembers;
import com.vgil.model.ChallengePoints;
import com.vgil.model.ChallengeTeam;

import static com.vgil.model.ApplicationConstants.*;

@Service("ChallengeBankBO")
public class ChallengeBankBOImpl extends BaseAction implements ChallengeBankBO {



	ArrayList<String> allCompanies;
	Map<String,List<String>> allPlants;
	List<String> plants;
	List<String> challengeType = new ArrayList<String>();
	List<String> challengeCategory = new ArrayList<String>();
	List<String> priority = new ArrayList<String>();


	private static final long serialVersionUID = 1L;
	ChallengeBankDAO challengeBankDAO;
	ApplicationSettingsBo applicationSettingsBo;

	public ApplicationSettingsBo getApplicationSettingsBo() {
		return applicationSettingsBo;
	}
	public void setApplicationSettingsBo(ApplicationSettingsBo applicationSettingsBo) {
		this.applicationSettingsBo = applicationSettingsBo;
	}
	public ChallengeBankDAO getChallengeBankDAO() {
		return challengeBankDAO;
	}
	public void setChallengeBankDAO(ChallengeBankDAO challengeDao) {
		this.challengeBankDAO = challengeDao;
	}

	@Override
	public ChallengeMaster loadChallenge(String challengeNo) throws Exception{
		return challengeBankDAO.loadChallenge(challengeNo);
	} 

	@Override
	public void createChallenge(ChallengeMaster chBank) throws Exception {


		challengeBankDAO.createChallenge(chBank);




	}
	@Override
	public List<ChallengeMaster> getChallengeById(String company, Employee employee) {
		List<ChallengeMaster> responseList;
		responseList = challengeBankDAO.getChallengebyId(company, employee);
		return responseList;
	}
	@Override
	public List<ChallengeMaster> getChallengeForUserId(Employee employee) {
		List<ChallengeMaster> responseList;
		responseList = challengeBankDAO.getChallengeForUserId( employee);
		return responseList;
	}


	private void getDatabaseValues()
	{
		List<ApplicationSettings> applicationTable = getApplicationSettings();
		for(int iter=0; iter<applicationTable.size(); iter++ )
		{

			if(applicationTable.get(iter).getCodeType().equals(CATEGORY)){

				challengeCategory.add(applicationTable.get(iter).getValue());
			}

			else if(applicationTable.get(iter).getCodeType().equals(CHALLENGETYPE))
			{
				challengeType.add(applicationTable.get(iter).getValue());
			}

			else if(applicationTable.get(iter).getCodeType().equals(PRIORITY))
			{
				priority.add(applicationTable.get(iter).getValue());
			}

		}


	}
	@SuppressWarnings("unchecked")
	@Override
	public String validateChallengeEntries(File uploadedFile,Map<String, Object> session) throws Exception {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String error = BLANKSPACE;
		int lineCount = 0;
		int editedRecord = 0;

		try {

			fileReader = new FileReader(uploadedFile);
			bufferedReader = new BufferedReader(fileReader);
			String strLine;			
			getDatabaseValues();

			while ((strLine = bufferedReader.readLine()) != null) {




				if(lineCount>0)

				{   
					editedRecord ++;

					//Total Entries in the CSV or TXT file.
					String[] challengeString = strLine.split(",");
					if(challengeString.length != NUMBEROFENTRIESINCHALLENGEUPLOADFILE){
						throw new Exception(" Data not correct.Total 7 entries expected. Error on line no "+lineCount, new Exception());
					}



					//Checking Validation for Company
					if(BLANKSPACE.equals(challengeString[0].trim())){
						error = error + " Company Name can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}
					allCompanies = (ArrayList<String>)session.get("UserAccesibleCompany");
					if(!allCompanies.contains(challengeString[0].trim()))
					{
						error = error + "You are not Authorized to add Challenges for the Company "+challengeString[0].trim()+". Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}


					//Checking Validation for Plant
					if(BLANKSPACE.equals(challengeString[1].trim())){
						error = error + " Plant Name can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}
					allPlants = (Map<String,List<String>>)session.get("UserAccesiblePlants");
					plants = (List<String>)allPlants.get(challengeString[0].trim());
					if(plants==null)
					{
						error = error + " The plant "+challengeString[1].trim()+" is not a part of Company's ("+challengeString[0].trim()+") Plants . Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}
					else if (!plants.contains(challengeString[1].trim()))
					{
						error = error + " The plant "+challengeString[1].trim()+" is not a part of Company's ("+challengeString[0].trim()+") Plants . Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}



					//Checking Validation for Challenge Type
					if(BLANKSPACE.equals(challengeString[2].trim())){
						error = error + " Challenge Type Name can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}
					if (!challengeType.contains(challengeString[2].trim()))
					{
						error = error + " The Challenge type "+challengeString[2].trim()+" is not a part of Standard Challenge types. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";					
					}


					//Checking Validation for Challenge Category
					if(BLANKSPACE.equals(challengeString[3].trim())){
						error = error + " Challenge Category can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}
					if (!challengeCategory.contains(challengeString[3].trim()))
					{
						error = error + " The Challenge Category "+challengeString[3].trim()+" is not a part of Standard Challenge Category . Error on line no "+lineCount+"."+LINESEPERATOR+"\n";					
					}


					//Checking Validation for Challenge Priority
					if(BLANKSPACE.equals(challengeString[4].trim())){
						error = error + " Priority can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}
					if (!priority.contains(challengeString[4].trim()))
					{
						error = error + " The Priority "+challengeString[4].trim()+" is not a part of Standard Priority List . Error on line no "+lineCount+"."+LINESEPERATOR+"\n";					
					}


					//Checking Validation for Challenge Description
					if(BLANKSPACE.equals(challengeString[5].trim())){
						error = error + " Description can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}

					//Checking Validation for Challenge Description
					if(BLANKSPACE.equals(challengeString[6].trim())){
						error = error + " Quantitative Measure of Success can not be empty. Error on line no "+lineCount+"."+LINESEPERATOR+"\n";
					}

				}
				lineCount++;
			}

		}catch (Exception e) {

			throw new Exception(e.getMessage(), e);
		}finally{
			try {
				if(bufferedReader != null )
					bufferedReader.close();
			} catch (IOException e) {
			}
			try {
				if(fileReader != null )
					fileReader.close();
			} catch (IOException e) {
			}
		}

		if(editedRecord == 0)
		{
			error = "Upload file does not have any record. Please upload a file with proper Records.";
			return error;
		}
		if(error.equals(BLANKSPACE))
		{
			return VALIDATION_PASS;
		}

		else
			return VALIDATION_FAIL;



	}


	@Override
	public int getChallengeBankCount(Employee empName) throws Exception {
		return challengeBankDAO.getChallengeBankCount(empName);
	}
	@Override
	public List<ChallengeMaster> getChallengeBankList(Employee employee) throws Exception {
		return challengeBankDAO.getChallengeBankList(employee);
	}
	@Override
	public int countByCriteriaForChallengeBank(String searchField,
			String searchOper, String searchString) throws SQLException {
		return challengeBankDAO.countByCriteriaForChallengeBank(searchField, searchOper, searchString);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengeMaster> findByCriteriaForChallengeBank(String searchField,
			String searchOper, String searchString, int from, int to)
					throws SQLException {
		return challengeBankDAO.findByCriteriaForChallengeBank(searchField, searchOper, searchString);
	}
	@Override
	public List<ChallengeMaster> getTotalSavingAcrossCompanywithCompanyList() {
		return challengeBankDAO.getTotalSavingAcrossCompany();

	}
	@Override
	public int getOpenChallengesCount() throws Exception {
		return challengeBankDAO.getOpenChallengesCount();
	}
	@Override
	public List<ChallengeMaster> getOpenChallengeList(int from, int to)
			throws Exception {
		return challengeBankDAO.getOpenChallengeList(from, to);
	}
	@Override
	public List<ChallengeMaster> getPursuedChallengeList(String userId)
			throws Exception {
		return challengeBankDAO.getPursuedChallengeList(userId);
	}
	@Override
	public void saveOrUpdateIdea(IdeaMaster ideaBank) {
		challengeBankDAO.saveOrUpdateIdea(ideaBank);
	}
	@Override
	public List<IdeaMaster> getChallengeIdeas(long challengeNo) throws Exception {
		return challengeBankDAO.getChallengeIdeas(challengeNo);
	}
	@Override
	public List<ChallengeMaster> getCompanyChallenges(String company) throws Exception {
		return challengeBankDAO.getCompanyChallenges(company);
	}
	@Override
	public void saveOrUpdateChallengeTracking(ChallengePoints trackChallenge)
			throws Exception {
		challengeBankDAO.saveOrUpdateChallengeTracking(trackChallenge);
	}

	@SuppressWarnings("unused")
	private void processChallengeTrackingForOwner(String userId,Long challengeNo,String challenge,String challengeType ,String status){
		try {
			ChallengePoints trackChallenge = new ChallengePoints();
			trackChallenge.setChallenge_id(challengeNo.toString());
			//	trackChallenge.setStatus(status);
			trackChallenge.setUser_id(userId);
			trackChallenge.setChallenge_id(challenge);
			boolean firstTimeChallenge = isUserPursuedFirstChallenge(userId, challengeNo);
			if(("Individual").equalsIgnoreCase(challengeType)){
				if(firstTimeChallenge){
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(500);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(1000);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(1000);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(1500);
					}
				}else{
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(1000);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(1000);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(1500);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(2500);
					}
				}
			}else if(("Functional").equalsIgnoreCase(challengeType)){
				if(firstTimeChallenge){
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(2000);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(3000);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(5000);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(25000);
					}
				}else{
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(2500);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(4000);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(6500);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(30000);
					}
				}					
			}else if(("Organizational").equalsIgnoreCase(challengeType)){
				if(firstTimeChallenge){
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(3000);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(4000);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(10000);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(50000);
					}
				}else{
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(3500);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(4500);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(15000);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(60000);
					}
				}
			}if(("Industrial").equalsIgnoreCase(challengeType)){
				if(firstTimeChallenge){
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(5000);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(5000);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(20000);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(100000);
					}
				}else{
					if(status.contains("Pursue")){
						trackChallenge.setPersue_points(7500);
					}else if(status.contains("Idea")){
						trackChallenge.setIdea_generation_points(7500);
					}else if(status.contains("Prototype")){
						trackChallenge.setPrototype_points(20000);
					}else if(status.contains("Scaleup")){
						trackChallenge.setScaleup_points(100000);
					}
				}
			}else{
				/*int teamSize = getTeamSizeForChallenge(challengeNo.intValue());
					int points = getPointsForTeam(challengeType, status,teamSize , firstTimeChallenge);
					if(teamSize >1){
						List<TrackChallenge> trackChallenges = getChallengePoints(challengeNo);
						for (Iterator iterator = trackChallenges.iterator(); iterator
								.hasNext();) {
							TrackChallenge trackChallenge2 = (TrackChallenge) iterator
									.next();
							trackChallenge2.setPoints(points);
							trackChallenge2.setEndDate(new Date());
							if(status.contains(trackChallenge2.getStatus())){
								continue;
							}
							trackChallenge2.setStatus(status);
							saveOrUpdateChallengeTracking(trackChallenge2);
						}
						return;
					}
					if(points != -1){
						trackChallenge.setPoints(points);
					}else{
						throw new Exception("Invalid input");
					}*/
			}
			saveOrUpdateChallengeTracking(trackChallenge);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void processChallengeTrackingForTeam(List<String> userIds,int challengeNo,String challenge,String challengeType ,String status){
		try {
			int teamSize = getTeamSizeForChallenge(challengeNo);
			for (Iterator<String> iterator = userIds.iterator(); iterator.hasNext();) {
				String userId = (String) iterator.next();
				ChallengePoints trackChallenge = new ChallengePoints();
				trackChallenge.setChallenge_id(Integer.toString(challengeNo));
				trackChallenge.setUser_id(userId);
				trackChallenge.setChallenge_id(challenge);
				boolean firstTimeChallenge = isUserPursuedFirstChallenge(userId, challengeNo);
				if(("Individual").equalsIgnoreCase(challengeType)){
					if(firstTimeChallenge){
						if(status.contains("Pursued")){
							trackChallenge.setTotal_points(0);
						}else if(status.contains("Idea")){
							trackChallenge.setTotal_points(300);
						}else if(status.contains("Prototype")){
							trackChallenge.setTotal_points(200);
						}else if(status.contains("Scaleup")){
							trackChallenge.setTotal_points(500);
						}
					}else{
						trackChallenge.setTotal_points(0);
					}
				}else{
					int points = getPointsForTeam(challengeType, status, teamSize, firstTimeChallenge);
					if(points != -1){
						trackChallenge.setTotal_points(points);
					}else{
						throw new Exception("Invalid input");
					}
				}
				saveOrUpdateChallengeTracking(trackChallenge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getPointsForTeam(String challengeType, String status,int teamSize, boolean firstTimeChallenge){
		if(firstTimeChallenge){
			if(("Functional").equalsIgnoreCase(challengeType)){
				if(status.contains("Pursued")){
					return 2000/teamSize;
				}else if(status.contains("Idea")){
					return 3000/teamSize;
				}else if(status.contains("Prototype")){
					return 5000/teamSize;
				}else if(status.contains("Scaleup")){
					return 15000/teamSize;
				}						
			}
			if(("Organizational").equalsIgnoreCase(challengeType)){
				if(status.contains("Pursued")){
					return 3000/teamSize;
				}else if(status.contains("Idea")){
					return 4000/teamSize;
				}else if(status.contains("Prototype")){
					return 10000/teamSize;
				}else if(status.contains("Scaleup")){
					return 50000/teamSize;
				}
			}
			if(("Industrial").equalsIgnoreCase(challengeType)){
				if(status.contains("Pursued")){
					return 5000/teamSize;
				}else if(status.contains("Idea")){
					return 5000/teamSize;
				}else if(status.contains("Prototype")){
					return 20000/teamSize;
				}else if(status.contains("Scaleup")){
					return 100000/teamSize;
				}
			}
		}else{
			if(("Functional").equalsIgnoreCase(challengeType)){
				if(status.contains("Pursued")){
					return 2500/teamSize;
				}else if(status.contains("Idea")){
					return 4000/teamSize;
				}else if(status.contains("Prototype")){
					return 6500/teamSize;
				}else if(status.contains("Scaleup")){
					return 30000/teamSize;
				}
			}
			if(("Organizational").equalsIgnoreCase(challengeType)){
				if(status.contains("Pursued")){
					return 3500/teamSize;
				}else if(status.contains("Idea")){
					return 4500/teamSize;
				}else if(status.contains("Prototype")){
					return 15000/teamSize;
				}else if(status.contains("Scaleup")){
					return 60000/teamSize;
				}
			}
			if(("Industrial").equalsIgnoreCase(challengeType)){
				if(status.contains("Pursued")){
					return 7500/teamSize;
				}else if(status.contains("Idea")){
					return 7500/teamSize;
				}else if(status.contains("Prototype")){
					return 20000/teamSize;
				}else if(status.contains("Scaleup")){
					return 100000/teamSize;
				}	
			}
		}
		return -1;
	}
	@Override
	public boolean isUserPursuedFirstChallenge(String userId, long challengeNo)
			throws Exception {
		return challengeBankDAO.isUserPursuedFirstChallenge(userId, challengeNo);
	}
	public int getTeamSizeForChallenge(int challengeNo){
		try {
			int teamSize = challengeBankDAO.getTeamSizeForChallenge(challengeNo);
			if(teamSize == 0 || teamSize == -1){
				teamSize = 1;
			}else{
				return teamSize;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public List<ChallengePoints> getChallengePoints(Long id) throws Exception {
		return challengeBankDAO.getChallengePointsList(id.intValue());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getALLIdeaSaving() {
		return challengeBankDAO.getALLIdeaSaving();
	}
	@Override
	public PersonalTrackerVO getPersoanlTrakerDetails(Employee employee)
			throws Exception {

		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getALLIdeaSavingForEmployee(Employee employee) {
		return challengeBankDAO.getALLIdeaSavingForEmployee(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getALLIdeasForEmployee(Employee employee) {
		return challengeBankDAO.getALLIdeasForEmployee(employee);
	}

	@SuppressWarnings("unchecked")
	public List<ChallengePoints> getChallengePointsTracker(Employee employee) {
		return challengeBankDAO.getChallengePointsTracker(employee);

	}
	@Override
	public IdeaMaster loadIdea(long ideaNo) throws Exception {
		return challengeBankDAO.loadIdea(ideaNo);
	}
	@Override
	public List<ChallengePoints> getChallengePoints(Employee employee) throws Exception {

		Set<String> challengeNos =  new HashSet<String>(); 
		List<ChallengeMaster> challengeList = challengeBankDAO.getChallengeBankList(employee);
		for (Iterator<ChallengeMaster> iterator = challengeList.iterator(); iterator.hasNext();) {
			ChallengeMaster challenge = (ChallengeMaster) iterator.next();
			challengeNos.add((challenge.getChallenge_id()));
		}
		return challengeBankDAO.getChallengePointsList(challengeNos);
	}
	@Override
	public List<IdeaMaster> getChallengeIdeas(List challengeNos)
			throws Exception {
		return challengeBankDAO.getChallengeIdeas(challengeNos);
	}

	@Override
	public List<ChallengeMaster> getChallengeBankListForCompany(
			Employee employee) throws Exception {
		return challengeBankDAO.getChallengeBankListForCompany(employee);
	}
	@Override
	public List<ApplicationSettings> getApplicationSettings() {

		return challengeBankDAO.getApplicationSettings();
	}

	public int getLastSerialNumber()
	{
		return challengeBankDAO.getLastSerialNumber();
	}

	public String getCompanybyId (String id)
	{
		return challengeBankDAO.getCompanybyId(id);
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}
	public List<ApplicationSettings> getChallengeDetails(String param)
	{
		return challengeBankDAO.getChallengeDetails(param);
	}
	@Override
	public String saveOrUpdateChallenge(ChallengeMaster cm) {

		return challengeBankDAO.saveOrUpdateChallenge(cm);
	}
	public int getTeamLatestSerialno()
	{
		return challengeBankDAO.getTeamLatestSerialno();
	}

	public int getMemberLatestSerialno()
	{
		return challengeBankDAO.getMemberLatestSerialno();
	}
	@Override
	public boolean saveOrUpdateChallengeTeam(ChallengeTeam ct) {


		return challengeBankDAO.saveOrUpdateChallengeTeam(ct);

	}
	@Override
	public boolean saveOrUpdateChallengeMembers(TeamMembers tm) {

		return challengeBankDAO.saveOrUpdateChallengeMembers(tm);

	}


}

