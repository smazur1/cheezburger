package customTools;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.ChActivity;
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChComment;
import model.ChHrrole;
import model.ChJobactivity;
import model.ChJobtype;
import model.ChRoleActivity;
import model.ChUser;

public class DBUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CheezburgerHR");
	
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	public static void insertApplication(ChApplication application) {		
		long appId = DBUtil.getNewApplicationId();
		application.setAppid(appId);
		application.setAppstatus("I");
		DBUtil.insert(application);
		DBUtil.populateApplicationActivity(application);
	}
	
	public static <T> void insert(Object T) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(T);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static <T> void update(Object T) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(T);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<ChJobtype> getJobList() {
		EntityManager em = emf.createEntityManager();
		List<ChJobtype> jobList = null;
		try {
			jobList = em.createNamedQuery("ChJobtype.findAll", ChJobtype.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return jobList;
	}
	
	public static ChJobtype getJobtypeByID(long jobID) {
		EntityManager em = emf.createEntityManager();
		String qString = "SELECT c FROM ChJobtype c WHERE c.jobId = " + jobID;
		TypedQuery<ChJobtype> q = em.createQuery(qString, ChJobtype.class);
		ChJobtype jobType = null;
		try {
			jobType = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return jobType;
	}
	
	public static ChUser findUser(String username, String password) {
		EntityManager em = emf.createEntityManager();
		String qString = "SELECT c FROM ChUser c WHERE c.username = '" + username + "' AND c.password = '" + password + "'";
		TypedQuery<ChUser> q = em.createQuery(qString, ChUser.class);
		ChUser foundUser = null;
		try {
			foundUser = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return foundUser;
	}
	
	public static long getNewApplicationId() {
		EntityManager em = emf.createEntityManager();
		String qString = "SELECT (max(c.appid) + 1) FROM ChApplication c";
		
		Query q = em.createQuery(qString, ChApplication.class);
		long newId = 1;
		
		try {
			newId = (long) q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return newId;
	}
	
	public static long getNewApplicationActivityId() {
		EntityManager em = emf.createEntityManager();
		String qString = "SELECT (max(c.appactid) + 1) FROM ChApplicationActivity c";
		
		Query q = em.createQuery(qString, ChApplication.class);
		long newId = 1;
		
		try {
			newId = (long) q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return newId;
	}
	
	public static void populateApplicationActivity(ChApplication popApp) {
		Date now = new Date();
		
		EntityManager em = emf.createEntityManager();
		String qString = "SELECT c FROM ChJobactivity c WHERE c.chJobtype.jobId = " + popApp.getChJobtype().getJobId();
		TypedQuery<ChJobactivity> q = em.createQuery(qString, ChJobactivity.class);
		List<ChJobactivity> jobActivityList = null;
		try {
			jobActivityList = q.getResultList();
			for(ChJobactivity appJobActivity: jobActivityList) {
				ChActivity activity = appJobActivity.getChActivity();
				ChApplicationActivity appActivity = new ChApplicationActivity();
				appActivity.setAppactid(DBUtil.getNewApplicationActivityId());
				appActivity.setChApplication(popApp);
				appActivity.setChActivity(activity);
				appActivity.setActstatus("I");
				appActivity.setActmoddate(now);
				DBUtil.insert(appActivity);
				DBUtil.populateComment(appActivity);
				if(activity.getActid() == 4) {
					DrugTestUtil.generateDrugTests(appActivity);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static List<ChApplication> getApplicationList() {
		EntityManager em = emf.createEntityManager();
		List<ChApplication> appList = null;
		try {
			appList = em.createNamedQuery("ChApplication.findAll", ChApplication.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return appList;
	}
	
	public static List<ChApplicationActivity> getApplicationActivityList(ChHrrole role, ChApplication app) {
		EntityManager em = emf.createEntityManager();
		List<ChApplicationActivity> appActList = null;
		List<ChRoleActivity> roleActivityList = role.getChRoleActivities();
		String qString = "SELECT c FROM ChApplicationActivity c WHERE c.chApplication.appid = " + app.getAppid() + " AND (";
		for(int i = 0; i < roleActivityList.size(); i++) {
			if(i != 0) {
				qString += " OR ";
			}
			qString += "c.chActivity.actid = " + roleActivityList.get(i).getChActivity().getActid();
		}
		qString += ") ORDER BY c.appactid";
		TypedQuery<ChApplicationActivity> q = em.createQuery(qString, ChApplicationActivity.class);
		try {
			appActList = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return appActList;
	}
	
	public static LinkedHashMap<ChApplicationActivity, String> getAppActAccessMap(ChHrrole role, ChApplication app) {
		EntityManager em = emf.createEntityManager();
		String qString;
		ChRoleActivity roleAct;
		LinkedHashMap<ChApplicationActivity, String> appActAccessMap = new LinkedHashMap<ChApplicationActivity, String>();
		List<ChApplicationActivity> appActList =DBUtil.getApplicationActivityList(role, app);
		try {
		for(ChApplicationActivity appAct: appActList) {
			qString = "SELECT c FROM ChRoleActivity c WHERE c.chHrrole.hrId = " + role.getHrId() + " AND c.chActivity.actid = " + appAct.getChActivity().getActid();
			TypedQuery<ChRoleActivity> q = em.createQuery(qString, ChRoleActivity.class);
			roleAct = q.getSingleResult();
			appActAccessMap.put(appAct, roleAct.getRaaccess());
		}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return appActAccessMap;
	}
	
	public static ChApplication getApplicationByID(long appid) {
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM ChApplication c WHERE c.appid = " + appid;
		TypedQuery<ChApplication> q = em.createQuery(qString, ChApplication.class);
		ChApplication app = null;
		try {
			app = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return app;
	}
	
	public static ChApplicationActivity getApplicationActivityByID(long appactid) {
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM ChApplicationActivity c WHERE c.appactid = " + appactid;
		TypedQuery<ChApplicationActivity> q = em.createQuery(qString, ChApplicationActivity.class);
		ChApplicationActivity app = null;
		try {
			app = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return app;
	}
	
	public static ChApplicationActivity getApplicationActivityByID(long appid, long actid) {
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM ChApplicationActivity c WHERE c.chApplication.appid = " + appid + " AND c.chActivity.actid = " + actid;
		TypedQuery<ChApplicationActivity> q = em.createQuery(qString, ChApplicationActivity.class);
		ChApplicationActivity app = null;
		try {
			app = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return app;
	}
	
	public static void populateComment(ChApplicationActivity appAct) {
		ChComment newComment = new ChComment();
		long commentId = DBUtil.getNewCommentId();
		Date now = new Date();
		newComment.setComid(commentId);
		newComment.setChApplicationActivity(appAct);
		newComment.setComments("");
		newComment.setModdate(now);	
		DBUtil.insert(newComment);
	}
	
	public static long getNewCommentId() {
		EntityManager em = emf.createEntityManager();
		String qString = "SELECT (max(c.comid) + 1) FROM ChComment c";
		
		Query q = em.createQuery(qString, ChComment.class);
		long newId = 1;
		
		try {
			newId = (long) q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return newId;
	}
	
	public static ChComment getCommentByAppActId(long appActId) {
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM ChComment c WHERE c.chApplicationActivity.appactid = " + appActId;
		TypedQuery<ChComment> q = em.createQuery(qString, ChComment.class);
		ChComment comment = null;
		try {
			comment = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return comment;
	}
}
