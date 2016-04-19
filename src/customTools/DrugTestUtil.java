package customTools;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.ChApplicationActivity;
import model.ChDrugScreen;
import customTools.DBUtil;

public class DrugTestUtil {
	
	public static ChDrugScreen getTestByTypeAndActAppId(String testType, long appActId) {
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM ChComment c WHERE c.chApplicationActivity.appactid = " + appActId + " AND c.testtype = '" + testType + "'";
		TypedQuery<ChDrugScreen> q = em.createQuery(qString, ChDrugScreen.class);
		ChDrugScreen drugScreen = null;
		try {
			drugScreen = q.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return drugScreen;
	}
	
	public static void generateDrugTests(ChApplicationActivity appAct) {
		long drugTestId = DrugTestUtil.getNewDrugScreenId();
		Date now = new Date();
		
		ChDrugScreen standardPanel = new ChDrugScreen();
		standardPanel.setDsid(drugTestId);
		standardPanel.setChApplicationActivity(appAct);
		standardPanel.setTesttype("Standard Panel");
		standardPanel.setResults("");
		standardPanel.setModdate(now);
		DBUtil.insert(standardPanel);
		
		ChDrugScreen dotTesting = new ChDrugScreen();
		dotTesting.setDsid(drugTestId + 1);
		dotTesting.setChApplicationActivity(appAct);
		dotTesting.setTesttype("DOT Testing");
		dotTesting.setResults("");
		dotTesting.setModdate(now);
		DBUtil.insert(dotTesting);
		
		ChDrugScreen alcoholTesting = new ChDrugScreen();
		alcoholTesting.setDsid(drugTestId + 2);
		alcoholTesting.setChApplicationActivity(appAct);
		alcoholTesting.setTesttype("Alcohol Testing");
		alcoholTesting.setResults("");
		alcoholTesting.setModdate(now);
		DBUtil.insert(alcoholTesting);
	}
	
	public static long getNewDrugScreenId() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT (max(c.dsid) + 1) FROM ChDrugScreen c";
		
		Query q = em.createQuery(qString, ChDrugScreen.class);
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
}
