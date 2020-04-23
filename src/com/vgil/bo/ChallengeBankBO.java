package com.vgil.bo;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.PersonalTrackerVO;
import com.vgil.model.TeamMembers;
import com.vgil.model.ChallengePoints;
import com.vgil.model.ChallengeTeam;

public interface ChallengeBankBO {
	public void createChallenge(ChallengeMaster challengBank) throws Exception;
	public void saveOrUpdateIdea(IdeaMaster ideaBank);
	public List<ChallengeMaster> getChallengeById(String company, Employee emp);
	public String validateChallengeEntries(File uploadedFile,Map<String, Object> session) throws Exception;
	public List<ChallengeMaster> getChallengeBankList(Employee employee) throws Exception;
	public List<ChallengeMaster> getPursuedChallengeList(String userId) throws Exception;
	public int getOpenChallengesCount() throws Exception;
	public List<ChallengeMaster> getOpenChallengeList(int from, int to) throws Exception;
	public int countByCriteriaForChallengeBank(String searchField,String searchOper, String searchString) throws SQLException;
	public List findByCriteriaForChallengeBank(String searchField,String searchOper, String searchString,int from, int to) throws SQLException;
	public List<ChallengeMaster> getTotalSavingAcrossCompanywithCompanyList();
	public List<ChallengeMaster> getCompanyChallenges(String companyID) throws Exception;
	public void saveOrUpdateChallengeTracking(ChallengePoints trackChallenge) throws Exception;
	public boolean isUserPursuedFirstChallenge(String userId, long challengeNo) throws Exception;
	public int getTeamSizeForChallenge(int challengeNo) throws Exception;
	public List getChallengePoints(Long id) throws Exception;
	public void processChallengeTrackingForTeam(List<String> userIds, int challengeNo, String challenge,
		String challengeType, String status);
	public List getALLIdeaSaving();
	public List getALLIdeaSavingForEmployee(Employee employee);
	public int getChallengeBankCount(Employee empName) throws Exception;
	List<IdeaMaster> getChallengeIdeas(long challengeNo) throws Exception;
	public PersonalTrackerVO getPersoanlTrakerDetails(Employee employee) throws Exception;
	List<ChallengeMaster> getChallengeForUserId(Employee employee);
	ChallengeMaster loadChallenge(String challengeNo) throws Exception;
	IdeaMaster loadIdea(long ideaNo) throws Exception;
	List getChallengePoints(Employee employee) throws Exception;
	List<IdeaMaster> getChallengeIdeas(List challengeNos) throws Exception;
	List<ChallengeMaster> getChallengeBankListForCompany(Employee employee) throws Exception;
	public List getALLIdeasForEmployee(Employee employee);
	public List<ApplicationSettings> getApplicationSettings();
	public int getLastSerialNumber();
	public String getCompanybyId (String id);
	public List<ApplicationSettings> getChallengeDetails(String parameter);
	public String saveOrUpdateChallenge(ChallengeMaster cm);
	public int getTeamLatestSerialno();
	public int getMemberLatestSerialno();
	public boolean saveOrUpdateChallengeTeam(ChallengeTeam ct);
	public boolean saveOrUpdateChallengeMembers(TeamMembers tm);

}
