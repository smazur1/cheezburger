

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChComment;
import customTools.DependencyCheck;

/**
 * Servlet implementation class ApplicationListServlet
 */
@WebServlet("/DisplayNationalityServlet")
public class DisplayNationalityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayNationalityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		ChApplication ca = (ChApplication) session.getAttribute("application" );
		long localactid = 1;  //  "NT"
		
	//	
		System.out.println("before dependency check");
		System.out.println("ca.getChJobtype().getJObId = " + ca.getChJobtype().getJobId());
		System.out.println("localactid = " + localactid);
		System.out.println("ca.getAppid = " + ca.getAppid());
		
	//	
		
		
	    ArrayList<ChApplicationActivity> depactlist = DependencyCheck.getListByJobId
	    		(ca.getChJobtype().getJobId(), localactid, ca.getAppid());		
		//  check if incomplete dependant activities exist
	    System.out.println("after dependency check");
		
	//	request.setAttribute("applicationList", applicationList);
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
	    request.setAttribute("nationalitycomment", currentComment.getComments());
	    
		request.getRequestDispatcher("Nationality.jsp").forward(request, response);
	}

}
