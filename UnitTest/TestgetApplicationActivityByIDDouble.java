import static org.junit.Assert.assertEquals;

import org.junit.Test;

import customTools.DBUtil;

import model.ChApplicationActivity;

public class TestgetApplicationActivityByIDDouble {
	@Test
	   public void test_getAppActivity() {
		ChApplicationActivity act=DBUtil.getApplicationActivityByID(1,1);
		
	      assertEquals(act.getActstatus(),"F") ;
	      
	   }
}
