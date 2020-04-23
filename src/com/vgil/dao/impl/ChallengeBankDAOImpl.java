package com.vgil.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vgil.dao.ChallengeBankDAO;
import com.vgil.model.ApplicationSettings;
import com.vgil.model.ChallengeMaster;
import com.vgil.model.Employee;
import com.vgil.model.IdeaMaster;
import com.vgil.model.PersonalTrackerVO;
import com.vgil.model.TeamMembers;
import com.vgil.model.ChallengePoints;
import com.vgil.model.ChallengeTeam;
import com.vgil.model.CompanyMaster;

import static com.vgil.model.ApplicationConstants.*;

public class ChallengeBankDAOImpl extends HibernateDaoSupport implements ChallengeBankDAO{

	@Override
	public ChallengeMaster loadChallenge(String challengeNo) throws Exception {
		
		ChallengeMaster challenge = null;
		try {
			challenge = getHibernateTemplate().load(ChallengeMaster.class, challengeNo);
		} catch (Exception e) {

		}
		return challenge;
	}

	@Override
	public void createChallenge(ChallengeMaster chBank) throws Exception {
	
		
		getHibernateTemplate().save(chBank);
			
		
		
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengeMaster> getChallengebyId(String company , Employee emp) {

		List<ChallengeMaster> returndata;
		getHibernateTemplate().setCacheQueries(true);  
		
		if("I7".equalsIgnoreCase(emp.getUserRole())){
			returndata = getHibernateTemplate().findByCriteria( DetachedCriteria.forClass(ChallengeMaster.class));

		} else {

			returndata = getHibernateTemplate().findByCriteria( DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq("company_id", emp.getCompanyID())));


		}
		
		
		return returndata;
	}

	@SuppressWarnings("unchecked")
	public List<ChallengeMaster> getChallengeForUserId( Employee employee) {


		getHibernateTemplate().setCacheQueries(true);
		List<ChallengeMaster> returndata;
		List<ChallengePoints> objList = new ArrayList<ChallengePoints>();

		objList = getHibernateTemplate().findByCriteria( DetachedCriteria.forClass(ChallengePoints.class)
				.add(Restrictions.eq("user_id", employee.getUser_id())));
		
        if(objList.size()!=0)
        {
		returndata = getHibernateTemplate().findByCriteria( DetachedCriteria.forClass(ChallengeMaster.class)
				.add(Restrictions.eq("challenge_id", objList.get(0).getChallenge_id())));

        }
        else returndata = null;
		return returndata;
	}

	/*@Override
	public int getTotalSavingAcrossCompany() {
		List<Challenge> objList = new ArrayList<Challenge>();
		objList = (List<Challenge>) getHibernateTemplate().loadAll(Challenge.class);
		int potentialRsMil = 0;
		for(Challenge challenge: objList){
			potentialRsMil = potentialRsMil + challenge.getPotentialRsMil();
		}
		return potentialRsMil;
	}*/

	public List<ChallengeMaster> getTotalSavingAcrossCompany() {
		getHibernateTemplate().setCacheQueries(true);  
		List<ChallengeMaster> objList = new ArrayList<ChallengeMaster>();
		objList = (List<ChallengeMaster>) getHibernateTemplate().loadAll(ChallengeMaster.class);
		return objList;
	}



	@Override
	public int getChallengeBankCount(Employee empName) throws SQLException {
		getHibernateTemplate().setCacheQueries(true);  
		Long count = (long) 0;
		if("I7".equalsIgnoreCase(empName.getUser().getRole())){
			count = (Long) getHibernateTemplate().find("SELECT COUNT(*) FROM Challenge").get(0);
		} else if ("ANCHOR".equalsIgnoreCase(empName.getUser().getRole())) {
			count = (Long) getHibernateTemplate().find("SELECT COUNT(*) FROM Challenge where company="+"'"+empName.getCompanyID()+"'").get(0);
		} else {
			count = (Long) getHibernateTemplate().find("SELECT COUNT(*) FROM Challenge where userid="+"'"+empName.getUser_id()+"'").get(0);
		}

		return count.intValue();
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public List<ChallengeMaster> getChallengeBankList(Employee employee)
			throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		if(ADMIN_USER.equalsIgnoreCase(employee.getUserRole())){
			return (List<ChallengeMaster>)getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq("challenge_status",CHALLENGEOPEN)));
		} else 
			
			return (List<ChallengeMaster>)getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq("company_id", employee.getCompanyID()))
					.add(Restrictions.eq("challenge_status",CHALLENGEOPEN)));
		
		
	
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengeMaster> getChallengeBankListForCompany(Employee employee)
			throws Exception {


		getHibernateTemplate().setCacheQueries(true);

		if("I7".equalsIgnoreCase(employee.getUserRole())){
			return getHibernateTemplate().loadAll(ChallengeMaster.class);
		} else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq("company_id", employee.getCompanyID()))
					.add(Restrictions.eq("challenge_status",CHALLENGEOPEN)));			
	}
	
	public int countByCriteriaForChallengeBank(String searchField,String searchOper, String searchString) throws SQLException{
		getHibernateTemplate().setCacheQueries(true);  
		if ("id".equals(searchField)){
			searchField = "id";
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq(searchField, Long.parseLong(searchString))))).size();
		}
		if (searchOper.equals("eq")){
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq(searchField, searchString)))).size();
		}
		else if (searchOper.equals("ne"))
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.ne(searchField, searchString)))).size();
		else if (searchOper.equals("lt")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.lt(searchField, searchString)))).size();
		else if (searchOper.equals("gt")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.gt(searchField, searchString)))).size();
		else if (searchOper.equals("bw")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.like(searchField, searchString + "%")))).size();
		else if (searchOper.equals("cn")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.like(searchField, "%" + searchString + "%")))).size();
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<ChallengeMaster> findByCriteriaForChallengeBank(String searchField,String searchOper, String searchString) throws SQLException{
		getHibernateTemplate().setCacheQueries(true);  
		if ("id".equals(searchField)){
			searchField = "id";
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq(searchField, Long.parseLong(searchString)))));
		}
		if (searchOper.equals("eq")){
			return ((List<ChallengeMaster>)getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq(searchField, searchString))));     	
		}
		else if (searchOper.equals("ne"))
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.ne(searchField, searchString))));
		else if (searchOper.equals("lt")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.lt(searchField, searchString))));
		else if (searchOper.equals("gt")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.gt(searchField, searchString))));
		else if (searchOper.equals("bw")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.like(searchField, searchString + "%"))));
		else if (searchOper.equals("cn")) 
			return (getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.like(searchField, "%" + searchString + "%"))));
		return null;
	}

	@Override
	public int getOpenChallengesCount() throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		Long count = (Long) getHibernateTemplate().find("SELECT COUNT(*) FROM Challenge where status like '%Open%'").get(0);
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengeMaster> getOpenChallengeList(int from, int to)
			throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		return (getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengeMaster.class)
				.add(Restrictions.like("status", "%Open%"))));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengeMaster> getPursuedChallengeList(String userId)
			throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		/*Map parameters = new HashMap();
		parameters.put("userId", userId);
		parameters.put("status", "Pursued");*/
		return (getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengeMaster.class)));
	}

	@Override
	public void saveOrUpdateIdea(IdeaMaster ideaBank) {
		//getHibernateTemplate().saveOrUpdate(ideaBank);
		IdeaMaster idea = null;
		try {
			if (Integer.parseInt(ideaBank.getIdea_id()) > 0)
				idea = getHibernateTemplate().load(IdeaMaster.class, ideaBank.getIdea_id());
		} catch (Exception e) {

		}
		if(idea != null && ideaBank != null){
			if(ideaBank.getLast_updated_date() != null && !"".equals(ideaBank.getLast_updated_date())){
				idea.setLast_updated_date(ideaBank.getLast_updated_date());
			}
			if(ideaBank.getIdea_status() != null && !"".equals(ideaBank.getIdea_status())){
				idea.setIdea_status(ideaBank.getIdea_status());
			}
			if(ideaBank.getIdea() != null && !"".equals(ideaBank.getIdea())){
				idea.setIdea(ideaBank.getIdea());
			}
			if(ideaBank.getFrame() != null && !"".equals(ideaBank.getFrame())){
				idea.setFrame(ideaBank.getFrame());
			}
			if(ideaBank.getImpact_level() != null && !"".equals(ideaBank.getImpact_level())){
				idea.setImpact_level(ideaBank.getImpact_level());
			}
			if(ideaBank.getImpact_value() != null && !"".equals(ideaBank.getImpact_value())){
				idea.setImpact_value(ideaBank.getImpact_value());
			}
			if(Integer.toString(ideaBank.getImplementation_time()) != null && !"".equals(ideaBank.getImplementation_time())){
				idea.setImplementation_time(ideaBank.getImplementation_time());
			}
			if(ideaBank.getInvestment_value() != null && !"".equals(ideaBank.getInvestment_value())){
				idea.setInvestment_value(ideaBank.getInvestment_value());
			}
			if(ideaBank.getIdea_priority() != null && !"".equals(ideaBank.getIdea_priority())){
				idea.setIdea_priority(ideaBank.getIdea_priority());
			}
			if(ideaBank.getUnknowns() != null && !"".equals(ideaBank.getUnknowns())){
				idea.setUnknowns(ideaBank.getUnknowns());
			}
		}
		if(idea != null){
			getHibernateTemplate().saveOrUpdate(idea);
		}else if (ideaBank != null){
			getHibernateTemplate().saveOrUpdate(ideaBank);	
		}else{

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IdeaMaster> getChallengeIdeas(long challengeNo) throws Exception {
		getHibernateTemplate().setCacheQueries(true);  	
		return (getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(IdeaMaster.class)
				.add(Restrictions.eq("challengNo", challengeNo))
				.add(Restrictions.ne("ideaStatus", "Closed"))        
				));

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengeMaster> getCompanyChallenges(String companyID) throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		List<ChallengeMaster> objList = new ArrayList<ChallengeMaster>();
		
		if(!companyID.equals(null))
		{
		objList  =  (List<ChallengeMaster>) getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengeMaster.class)
					.add(Restrictions.eq("company_id", companyID))
					.add(Restrictions.not(Restrictions.like("challenge_status", "%"+CHALLENGECLOSED+"%"))));
		}
		else
		{
		objList  =  (List<ChallengeMaster>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengeMaster.class)
				.add(Restrictions.not(Restrictions.like("challenge_status", "%"+CHALLENGECLOSED+"%"))));
		}
		return objList;
	}

	@Override
	public boolean isUserPursuedFirstChallenge(String userId, long challengeNo){
		getHibernateTemplate().setCacheQueries(true);  
		try {
			@SuppressWarnings("unchecked")
			List<ChallengePoints> trackChallenges = getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(ChallengePoints.class)
					.add(Restrictions.eq("userId", userId))
					.add(Restrictions.eq("buddy", false))
					.addOrder(Order.asc("challengeNo"))
					);
			if(trackChallenges.size() > 0){
				ChallengePoints trackChallenge = trackChallenges.get(0);
				if(trackChallenge.getChallenge_id().equals(challengeNo)){
					return true;
				}
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateChallengeTracking(ChallengePoints trackChallenge)
			throws Exception {
		List<ChallengePoints> objList = new ArrayList<ChallengePoints>();
		objList  =  (List<ChallengePoints>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengePoints.class)
				.add(Restrictions.eq("challengeNo", trackChallenge.getChallenge_id()))
				.add(Restrictions.eq("userId", trackChallenge.getUser_id())));
		if(objList.size()>0){
			ChallengePoints challenge = objList.get(0);
			if(challenge != null && trackChallenge != null){
				if(trackChallenge.getPersue_points() != 0 )
					challenge.setPersue_points(trackChallenge.getPersue_points() );
				if(trackChallenge.getIdea_generation_points() != 0 )
					challenge.setIdea_generation_points(trackChallenge.getIdea_generation_points() );
				if(trackChallenge.getPrototype_points() != 0 )
					challenge.setPrototype_points(trackChallenge.getPrototype_points() );
				if(trackChallenge.getScaleup_points() != 0 )
					challenge.setScaleup_points(trackChallenge.getScaleup_points());

				int totalPoints = challenge.getPersue_points() + challenge.getIdea_generation_points()+
						challenge.getPrototype_points() + challenge.getScaleup_points();
				challenge.setTotal_points(totalPoints);
			}
		}
		/*	if(trackChallenge.getStatus() != null && !"".equals(trackChallenge.getStatus().trim()))
					challenge.setStatus(trackChallenge.getStatus());*/
		//				if(trackChallenge.getStartDate() != null )
		//					challenge.setStartDate(trackChallenge.getStartDate());
		/*	if(trackChallenge.getEndDate() != null ){
					challenge.setEndDate(trackChallenge.getEndDate());
				}else{
					challenge.setEndDate(new Date());
				}
			}
			getHibernateTemplate().saveOrUpdate(challenge);
		}else{
			if(trackChallenge.getStartDate() == null)
				trackChallenge.setStartDate(new Date());
			getHibernateTemplate().saveOrUpdate(trackChallenge);
		}*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengePoints> getChallengePointsList(int challengeNo)
			throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		List<ChallengePoints> objList = new ArrayList<ChallengePoints>();
		objList  =  (List<ChallengePoints>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengePoints.class)
				.add(Restrictions.eq("challengeNo", challengeNo)));
		return objList;
	}

	@Override
	public int getTeamSizeForChallenge(int challengeNo) throws Exception {
		return (Integer) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengePoints.class)
				.add(Restrictions.eq("challengeNo", challengeNo))).size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getALLIdeaSaving() {
		getHibernateTemplate().setCacheQueries(true);  
		List<IdeaMaster> objList = new ArrayList<IdeaMaster>();
		objList  =  (List<IdeaMaster>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(IdeaMaster.class)
				.add(Restrictions.disjunction()
						.add(Restrictions.like("ideaStatus", "%Closed%"))
						.add(Restrictions.like("ideaStatus", "%Scale-Up%"))));
		return objList;
	}

	@Override
	public Map getChallengeSist(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonalTrackerVO getPersoanlTrakerDetails(Employee employee)
			throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		PersonalTrackerVO trackerVO = new PersonalTrackerVO();
		try{
			Long persueCount = (Long) getHibernateTemplate().
					find("SELECT COUNT(*) FROM Challenge where status like '%Pursued%' and userId = "+employee.getUser_id().trim()).get(0);
			Long ideaCount = (Long) getHibernateTemplate().
					find("SELECT COUNT(*) FROM Challenge where status like '%Idea%' and userId = "+employee.getUser_id().trim()).get(0);
			Long prototypeCount = (Long) getHibernateTemplate().
					find("SELECT COUNT(*) FROM Challenge where status like '%Prototype%' and userId = "+employee.getUser_id().trim()).get(0);
			Long scaleupCount = (Long) getHibernateTemplate().
					find("SELECT COUNT(*) FROM Challenge where status like '%Scaleup%' and userId = "+employee.getUser_id().trim()).get(0);

			trackerVO.setChallengesPursued(persueCount.intValue());
			trackerVO.setIdeaGenerated(ideaCount.intValue());
			trackerVO.setIdeaProtyping(prototypeCount.intValue());
			trackerVO.setIdeaImplemented(scaleupCount.intValue());



		}catch(Exception e){

		}
		return trackerVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getALLIdeaSavingForEmployee(Employee employee) {
		getHibernateTemplate().setCacheQueries(true);  
		List<IdeaMaster> objList = new ArrayList<IdeaMaster>();
		objList  =  (List<IdeaMaster>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(IdeaMaster.class)
				.add(Restrictions.like("idea_id", employee.getUser_id()))
				.add(Restrictions.disjunction()
						.add(Restrictions.like("idea_status", "%Closed%"))
						.add(Restrictions.like("idea_status", "%Prototyping%"))
						.add(Restrictions.like("idea_status", "%Scale-Up%"))));
		return objList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getALLIdeasForEmployee(Employee employee) {
		getHibernateTemplate().setCacheQueries(true);  
		List<IdeaMaster> objList = new ArrayList<IdeaMaster>();
		objList  =  (List<IdeaMaster>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(IdeaMaster.class)
				.add(Restrictions.like("user_id", employee.getUser_id())));
		return objList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getChallengePointsTracker(Employee employee) {
		getHibernateTemplate().setCacheQueries(true);  
		List<ChallengePoints> objList = new ArrayList<ChallengePoints>();
		objList  =  (List<ChallengePoints>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengePoints.class)
				.add(Restrictions.eq("user_id", employee.getUser_id())));
		return objList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChallengePoints> getChallengePointsList(Set challengeNos)
			throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		List<ChallengePoints> objList = new ArrayList<ChallengePoints>();
		objList  =  (List<ChallengePoints>) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(ChallengePoints.class)
				.add(Restrictions.in("challenge_id", challengeNos)));
		return objList;
	}


	@Override
	public IdeaMaster loadIdea(long ideaNo) {
		return getHibernateTemplate().load(IdeaMaster.class, ideaNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IdeaMaster> getChallengeIdeas(List challengeNos) throws Exception {
		getHibernateTemplate().setCacheQueries(true);  
		return (getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(IdeaMaster.class)
				.add(Restrictions.in("challenge_id", challengeNos))));
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ApplicationSettings> getApplicationSettings()
	{
		getHibernateTemplate().setCacheQueries(true);
		
		List<ApplicationSettings> categoryList = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ApplicationSettings.class));
		
		return categoryList;
	}
	
	
	@SuppressWarnings("unchecked")
	public int getLastSerialNumber()
	{
		int returnValue;
		getHibernateTemplate().setCacheQueries(true);
		
	
		List<Integer> valuesFromDatabase = new ArrayList<Integer>();
		valuesFromDatabase = getHibernateTemplate().find("select MAX(serial_no) from ChallengeMaster");
		if(valuesFromDatabase.size() == 1 &&  valuesFromDatabase.get(0) == null)
		{
			returnValue = 0;
		}
		else 
		returnValue= (Integer)valuesFromDatabase.get(0);
		
		return returnValue;
	}
	
	public String getCompanybyId (String id)
	{
		@SuppressWarnings("unchecked")
		List<CompanyMaster> companyList = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(CompanyMaster.class).add(Restrictions.eq("company_id", id)));
		return (companyList.get(0).getCompany_name());
	}
	
	@SuppressWarnings("unchecked")
	public List<ApplicationSettings> getChallengeDetails (String param)
	{
	
		List<ApplicationSettings> returnValue = null;
		
		if(param.equalsIgnoreCase(CATEGORYLIST))
		{
			returnValue = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ApplicationSettings.class).add(Restrictions.eq("codeType", CATEGORY)));
		}
		
		else if (param.equalsIgnoreCase(CHALLENGETYPELIST))
		{
			returnValue = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ApplicationSettings.class).add(Restrictions.eq("codeType", CHALLENGETYPE)));
		}
		else if (param.equalsIgnoreCase(PRIORITY))
		{
			returnValue = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ApplicationSettings.class).add(Restrictions.eq("codeType", PRIORITY)));
		}
		else if (param.equalsIgnoreCase(CHALLENGESTATUS))
		{
			returnValue = getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ApplicationSettings.class).add(Restrictions.eq("codeType", CHALLENGESTATUS)));
		}
		return returnValue;
	}

	@Override
	public String saveOrUpdateChallenge(ChallengeMaster cm) {

		getHibernateTemplate().saveOrUpdate(cm);
		return "SUCCESS";
	}
	
	@SuppressWarnings("unchecked")
	public int getTeamLatestSerialno()
	{
		int returnValue;
		getHibernateTemplate().setCacheQueries(true);
		
	
		List<Integer> valuesFromDatabase = new ArrayList<Integer>();
		valuesFromDatabase = getHibernateTemplate().find("select MAX(serial_no) from TeamMembers");
		if(valuesFromDatabase.size() == 1 &&  valuesFromDatabase.get(0) == null)
		{
			returnValue = 0;
		}
		else 
		returnValue= (Integer)valuesFromDatabase.get(0);
		
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public int getMemberLatestSerialno()
	{
		int returnValue;
		getHibernateTemplate().setCacheQueries(true);
		
	
		List<Integer> valuesFromDatabase = new ArrayList<Integer>();
		valuesFromDatabase = getHibernateTemplate().find("select MAX(serial_no) from ChallengeTeam");
		if(valuesFromDatabase.size() == 1 &&  valuesFromDatabase.get(0) == null)
		{
			returnValue = 0;
		}
		else 
		returnValue= (Integer)valuesFromDatabase.get(0);
		
		return returnValue;
	}

	@Override
	public boolean saveOrUpdateChallengeMembers(TeamMembers tm) {
		
		getHibernateTemplate().saveOrUpdate(tm);
		return true;
	}

	@Override
	public boolean saveOrUpdateChallengeTeam(ChallengeTeam ct) {
		
		getHibernateTemplate().saveOrUpdate(ct);
		return true;
	}


	
	
	
}
