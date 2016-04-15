import static org.junit.Assert.* ;

import org.junit.Test;

import customTools.DBUtil;;
public class TestInsert {
	@Test
	   public void test_importsingleproduct() {
		
	      System.out.println("Test if table has been sucessfully imported") ;
	      int id=pp.UserLogin("user1", "pass1");
	     // Subscription S = new Subscription(200,2) ;
	      assertEquals(id,1) ;
	   }
}
