package com.vgil.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.PersonalTrackerVO;
import com.vgil.model.TeamMembers;
import com.vgil.model.ChallengePoints;
import com.vgil.model.ChallengeTeam;

public interface ChallengeBankDAO {
	public void createChallenge(ChallengeMaster chBank) throws Exception;
	public void saveOrUpdateIdea(IdeaMaster ideaBank);
	public List<ChallengeMaster> getChallengebyId(String company, Employee emp);
	public int getChallengeBankCount(Employee companyName) throws Exception;
	public List<ChallengeMaster> getChallengeBankList(Employee employee) throws Exception;
	public List<ChallengeMaster> getPursuedChallengeList(String userId) throws Exception;
	public int getOpenChallengesCount() throws Exception;
	public List<ChallengeMaster> getOpenChallengeList(int from, int to) throws Exception;
	public int countByCriteriaForChallengeBank(String searchField,String searchOper, String searchString) throws SQLException;
	public List<ChallengeMaster> findByCriteriaForChallengeBank(String searchField,String searchOper, String searchString) throws SQLException;
	public List<ChallengeMaster> getTotalSavingAcrossCompany();
	public List<ChallengeMaster> getCompanyChallenges(String companyID) throws Exception;
	public void saveOrUpdateChallengeTracking(ChallengePoints trackChallenge) throws Exception;
	public boolean isUserPursuedFirstChallenge(String userId, long challengeNo) throws Exception;
	public int getTeamSizeForChallenge(int challengeNo) throws Exception;
	public ChallengeMaster loadChallenge(String challengeNo) throws Exception;
	public List<ChallengePoints> getChallengePointsList(int challengeNo) throws Exception;
	public List getALLIdeaSaving();
	Map getChallengeSist(Employee employee) throws Exception;
	List<IdeaMaster> getChallengeIdeas(long challengeNo) throws Exception;
	public PersonalTrackerVO getPersoanlTrakerDetails(Employee employee) throws Exception;
	public List<ChallengeMaster> getChallengeForUserId( Employee employee);
	public List getALLIdeaSavingForEmployee(Employee employee);
	public List getChallengePointsTracker(Employee employee);
	List<ChallengePoints> getChallengePointsList(Set challengeNos)
			throws Exception;
	IdeaMaster loadIdea(long ideaNo);
	List<IdeaMaster> getChallengeIdeas(List challengeNos) throws Exception;
	List<ChallengeMaster> getChallengeBankListForCompany(Employee employee) throws Exception;
	public List getALLIdeasForEmployee(Employee employee);
	public List<ApplicationSettings> getApplicationSettings();
	public int getLastSerialNumber();
	public String getCompanybyId (String id);
	public List<ApplicationSettings> getChallengeDetails(String param);
	public String saveOrUpdateChallenge(ChallengeMaster cm);
	public int getMemberLatestSerialno();
	public int getTeamLatestSerialno();
	public boolean saveOrUpdateChallengeMembers(TeamMembers tm);
	public boolean saveOrUpdateChallengeTeam(ChallengeTeam ct);
	
	
}
