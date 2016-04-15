package customTools;

import java.util.Date;
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
import model.ChJobactivity;
import model.ChJobtype;
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
		long newId = 0;
		
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
		long newId = 0;
		
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
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
