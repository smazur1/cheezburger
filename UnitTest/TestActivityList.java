import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;

import customTools.DBUtil;
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChHrrole;
import model.ChUser;
import model.ChUserRole;

public class TestActivityList {
	@Test
	   public void test_updatesingle() {
		ChUser currentUser=DBUtil.findUser("one", "111");
		ChUserRole role=currentUser.getChUserRoles().get(0);
		ChHrrole hr=role.getChHrrole();
	      System.out.println("Test if method has been sucessfully worked "+hr.getRoleCode()) ;  
	      ChApplication app=getApplicationByID(1);
	      System.out.println(app.getBirthday());
	      List<ChApplicationActivity> list=DBUtil.getApplicationActivityList(hr,app);
	      for(ChApplicationActivity act:list){
	    	  System.out.println(act.getChActivity().getActdescription()+" status: "+act.getActstatus());
	      }
	      assertEquals(list.get(0).getChActivity().getActcode(),"NT") ;
	      
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
}
