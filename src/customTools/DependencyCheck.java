package customTools;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.ChActivityDependency;
import model.ChApplicationActivity;



public class DependencyCheck {

	public DependencyCheck() {
		// TODO Auto-generated constructor stub
	}
	
	// test get dependent activity
		

	@SuppressWarnings("finally")
	public static ArrayList<ChApplicationActivity> getListByJobId(long _job_id, long _actid, long _appid) {
		//
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
			
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

	@SuppressWarnings("finally")
	public static ChApplicationActivity getDepStatus(long _appid, ChActivityDependency _depact) {
		//
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
		
			return chaa;
		}

	}	

}
