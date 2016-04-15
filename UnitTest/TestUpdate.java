import static org.junit.Assert.* ;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import customTools.DBUtil;
import model.ChApplicationActivity;
import model.ChJobtype;


public class TestUpdate {
	@Test
	   public void test_updatesingle() {
		Date d=new Date();
		ChApplicationActivity lol=new ChApplicationActivity();
		lol=getActivity(1);
		lol.setActstatus("F");
		lol.setActmoddate(d);
		DBUtil.update(lol);
	      System.out.println("Test if table has been sucessfully inserted") ;     
	      assertEquals(getActivity(1).getActstatus(),"F") ;
	   }
	public static ChApplicationActivity getActivity(long appactid) {
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM ChApplicationActivity c WHERE c.appactid = " + appactid;
		TypedQuery<ChApplicationActivity> q = em.createQuery(qString, ChApplicationActivity.class);
		ChApplicationActivity activity = null;
		try {
			activity = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return activity;
	}
}
