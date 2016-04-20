

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
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChComment;

/**
 * Servlet implementation class DisplayApprovalServlet
 */
@WebServlet("/DisplayApprovalServlet")
public class DisplayApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		ChApplication ca = (ChApplication) session.getAttribute("application" );
		long localactid = 10;  //  "AP"
		
		
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
	    request.setAttribute("approvalcomment", currentComment.getComments());
	    
		request.getRequestDispatcher("Approval.jsp").forward(request, response);
	}

}
