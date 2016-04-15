package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ChApplication;
import model.ChJobtype;

public class DBUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CheezburgerHR");
	
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	public static void insertApplication(ChApplication application) {
		EntityManager em = emf.createEntityManager();
		
		String qString = "SELECT c.appid From ChApplication c ORDER BY c.appid DESC";
		long appId = em.createQuery(qString).getFirstResult();
		application.setAppid(appId);
		
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(application);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
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
		String qString = "SELECT c FROM ChJobtype c where c.jobId = " + jobID;
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
	
}
