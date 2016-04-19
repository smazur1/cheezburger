

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import customTools.DependencyCheck;
import customTools.DrugTestUtil;
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChComment;
import model.ChDrugScreen;

/**
 * Servlet implementation class DisplayDrugScreenServlet
 */
@WebServlet("/DisplayDrugScreenServlet")
public class DisplayDrugScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDrugScreenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		ChApplication ca = (ChApplication) session.getAttribute("application" );
		long localactid = 4;  //  "GI"
		
		
	    ArrayList<ChApplicationActivity> depactlist = DependencyCheck.getListByJobId
	    		(ca.getChJobtype().getJobId(), localactid, ca.getAppid());		
		//  check if incomplete dependent activities exist
		
	    if (!depactlist.isEmpty()) {
	    	request.setAttribute("depmessage", "There are dependent activities for this application");
	    	request.setAttribute("depflag", "1");
	    } else {
	    	request.setAttribute("depflag", "0");
	    }
	    request.setAttribute("depactlist", depactlist);
	    
	    //get current comment from table and put into comment field
	    ChApplicationActivity currentAppAct = DBUtil.getApplicationActivityByID(ca.getAppid(), localactid);
	    ChComment currentComment = DBUtil.getCommentByAppActId(currentAppAct.getAppactid());
	    ChDrugScreen standardpanel=DrugTestUtil.getTestByTypeAndActAppId("S",currentAppAct.getAppactid());
	    ChDrugScreen dottesting=DrugTestUtil.getTestByTypeAndActAppId("D",currentAppAct.getAppactid());
	    ChDrugScreen alcoholtesting=DrugTestUtil.getTestByTypeAndActAppId("A",currentAppAct.getAppactid());
	    request.setAttribute("drugscreencomment", currentComment.getComments());
	    request.setAttribute("standardpanel", standardpanel);
	    request.setAttribute("dottesting", dottesting);
	    request.setAttribute("alcoholtesting", alcoholtesting);
		request.getRequestDispatcher("DrugScreen.jsp").forward(request, response);
	}

}
