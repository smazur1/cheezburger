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


public class Samtest2 {


	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CheezburgerHR");

	public static EntityManagerFactory getEmFactory() {
		return emf;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long actid = 10;
		String status = "I";
		long jobid = 2;
		long appid = 1;
		ArrayList<ChApplicationActivity> incompletedepactlist = null;
		
		//		testSQL(actid, status);
		// testjdbc(actid, status);
		 incompletedepactlist = getListByJobId(jobid, actid, appid);

		if (incompletedepactlist != null) {
			for(ChApplicationActivity depstatus: incompletedepactlist) {
				System.out.println("returned app id = " + depstatus.getChApplication().getAppid());
				System.out.println("returned dep activity  = " + depstatus.getChActivity().getActid());
				System.out.println("returned dep status = " + depstatus.getActstatus());
		
		
			}
		}
	}

	// test get dependent activity


	///

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
			System.out.println("In listbyjobid");
			if (deplist != null) {
				for(ChActivityDependency depact: deplist) {
					System.out.println("dep activity = " + depact.getChActivity1().getActid());
					
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

		ChApplicationActivity incompletedepact = null;


		try {

			incompletedepact = q.getSingleResult();
	//		if (incompletedepact == null || incompletedepact.isEmpty())
	//			depstatuslist = null;

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			em.close();
			System.out.println("In depStatusList");
	//		if (depstatuslist != null) {
	//			for(ChApplicationActivity depstatus: depstatuslist) {
	//				System.out.println("app id = " + depstatus.getChApplication().getAppid());
	//				System.out.println("dep activity in depstatus = " + depstatus.getChActivity().getActid());
	//				System.out.println("dep status = " + depstatus.getActstatus());
	//				
	//				getDepStatusList(_appid, depact);
	//				
				
			
			return incompletedepact;
		}
		
	}	
	

	//

	public static void testjdbc(long _actid, String _status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//  con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");



			//      String query = "select count(*) from tableName";

			String qString = "select count(*) from ch_application_activity aa "
					+ " inner join ch_activity_dependency ad on ad.depactid = aa.actid "
					+ " inner join ch_application ap on aa.appid = ap.appid "
					+ " and ad.job_id = ap.job_id "
					+ " where ad.actid = 10 "
					+ " and aa.actstatus = 'I'" ;


			//		      qString.setString(1,_actid);
			//		      qString.setString(2,_status);
			long count = 0;




			pstmt = con.prepareStatement(qString);
			rs =  pstmt.executeQuery();
			if (rs.next()) {
				int numberOfRows = rs.getInt(1);
				System.out.println("numberOfRows= " + numberOfRows);		  	

			}




			//	while(rs.next()){
			//		System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" +
			//	    rs.getString(3) + "\t" + rs.getString(4));
			//		System.out.println(rs.getString(2));
			//		System.out.println(rs.getString(3));
			//		System.out.println(rs.getString(4));



		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}



	public static void testSQL(long _actid, String _actstatus) {
		EntityManager em = emf.createEntityManager();
		//

		em.getTransaction().begin();



		//            List<>?> list = em.createNativeQuery("Select employee_id, "
		//            		+ "employee_name from employee", Employee.class).getResultList();
		//
		//            System.out.println(list);
		//
		String qString = "select count(*) from ch_application_activity aa "
				+ " inner join ch_activity_dependency ad on ad.depactid = aa.actid "
				+ " inner join ch_application ap on aa.appid = ap.appid "
				+ " and ad.job_id = ap.job_id "
				+ " where ad.actid = :actid "
				+ " and aa.actstatus = :actstatus" ;



		//           BigDecimal sum = (List)em.createNativeQuery("SELECT SUM(p.price*l.quantity) 
		//                   FROM orders o JOIN orderlineitems l ON o.pono=l.pono 
		//                    JOIN products p ON l.prod_id=p.prod_id 
		//                    JOIN suppliers s ON p.sup_id=s.sup_id WHERE sup_name =?1")
		//                                .setParameter(1, sup_name)
		//                                .getSingleResult();           



		//



		//	String qString = "SELECT COUNT(caa) from ChApplicationActivity caa inner join "
		//			+ "ChActivityDependency cad on cad.actid = :actid "
		//			+ "and cad.depactid = caa.actid and caa.actstatus = :actstatus "
		//			+ "inner join ChApplication ca on caa.appid = ca.appid "
		//			+ "and cad.job_id = ca.job_id";







		long count = 0;


		try {
			Query q = 
					em.createNativeQuery(qString);

			q.setParameter("actid", _actid);
			q.setParameter("actstatus", _actstatus);

			count = (long) q.getSingleResult();

			//

			System.out.println("count = " + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}



	}


}

