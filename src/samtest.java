import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.ChApplication;
import model.ChApplicationActivity;
import model.ChJobactivity;
import model.ChActivity;
import model.ChActivityDependency;
import model.ChJobtype;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class samtest {


	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CheezburgerHR");

	public static EntityManagerFactory getEmFactory() {
		return emf;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long actid = 6;
		String status = "I";
		long jobid = 2;
		long appid = 1;
		ArrayList<ChApplicationActivity> incompletedepactlist = null;

		incompletedepactlist = getListByJobId(jobid, actid, appid);

		if (incompletedepactlist != null) {
			for(ChApplicationActivity depstatus: incompletedepactlist) {
				System.out.println("returned app id = " + depstatus.getChApplication().getAppid());
				System.out.println("returned dep activity  = " + depstatus.getChActivity().getActid());
				System.out.println("returned dep status = " + depstatus.getActstatus());

			}
		}
		if (incompletedepactlist.isEmpty()) {
			System.out.println("here in isEmpty");
		} 
		if (incompletedepactlist == null) {
			System.out.println("here in null");
		}
		System.out.println("Finished");
	}

	// test get dependent activity



	public static ArrayList<ChApplicationActivity> getListByJobId(long _job_id, long _actid, long _appid) {
		//
		EntityManager em = getEmFactory().createEntityManager();
		String qString = "Select c from ChActivityDependency c where c.chJobtype.jobId = :jobId "
				+ " and c.chActivity2.actid = :actid ";
		TypedQuery<ChActivityDependency> q = em.createQuery(qString, model.ChActivityDependency.class);
		q.setParameter("jobId", _job_id);
		q.setParameter("actid", _actid);

		List<ChActivityDependency> deplist = null;
		ArrayList<ChApplicationActivity> incompletedeplist = new ArrayList<ChApplicationActivity>();
		ChApplicationActivity indepact = null;

		try {

			deplist = q.getResultList();
			if (deplist == null || deplist.isEmpty())
				deplist = null;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();

			if (deplist != null) {
				for(ChActivityDependency depact: deplist) {
					//				System.out.println("dep activity = " + depact.getChActivity1().getActid());

					indepact = getDepStatus (_appid, depact); 

					if (indepact != null) {
						incompletedeplist.add(indepact);
					}
				}
			}
			return incompletedeplist;
		}
	}

	public static ChApplicationActivity getDepStatus(long _appid, ChActivityDependency _depact) {
		//
		EntityManager em = getEmFactory().createEntityManager();
		String qString = "Select aa from ChApplicationActivity aa "
				+ " where "
				+ " aa.chApplication.appid = :appid and "
				+ " aa.chActivity.actid = :actid and "
				+ " aa.actstatus = :actstatus ";

		TypedQuery<ChApplicationActivity> q = em.createQuery(qString, model.ChApplicationActivity.class);
		q.setParameter("appid", _appid);
		q.setParameter("actid", _depact.getChActivity1().getActid());
		q.setParameter("actstatus", "I" );

		List<ChApplicationActivity> incompleteactlist = null;
		ChApplicationActivity chaa = null;

		try {

			incompleteactlist = q.getResultList();
			if (incompleteactlist == null || incompleteactlist.isEmpty()) {
				chaa = null;
			} else {
				chaa = incompleteactlist.get(0);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			em.close();
			//		System.out.println("In depStatusList");
			//		if (depstatuslist != null) {
			//			for(ChApplicationActivity depstatus: depstatuslist) {
			//				System.out.println("app id = " + depstatus.getChApplication().getAppid());
			//				System.out.println("dep activity in depstatus = " + depstatus.getChActivity().getActid());
			//				System.out.println("dep status = " + depstatus.getActstatus());
			//				
			//				getDepStatusList(_appid, depact);
			//				
			return chaa;
		}

	}	

}




