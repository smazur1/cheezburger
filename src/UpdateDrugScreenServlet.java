

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import customTools.DrugTestUtil;
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChComment;
import model.ChDrugScreen;

/**
 * Servlet implementation class UpdateDrugScreenServlet
 */
@WebServlet("/UpdateDrugScreenServlet")
public class UpdateDrugScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDrugScreenServlet() {
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
		HttpSession session = request.getSession();
		String sendTo = "ApplicationActivityListServlet";
		Date now = new Date();
		ChApplication application = (ChApplication) session.getAttribute("application");

		
		ChApplicationActivity appAct = DBUtil.getApplicationActivityByID(application.getAppid(), 4);
		appAct.setActmoddate(now);

		String alteredComment = request.getParameter("drugscreencomment");
		ChComment currentComment = DBUtil.getCommentByAppActId(appAct.getAppactid());

		currentComment.setComments(alteredComment);
		currentComment.setModdate(now);
		DBUtil.update(currentComment);
		
		ChDrugScreen standardpanel=DrugTestUtil.getTestByTypeAndActAppId("S",appAct.getAppactid());
	    ChDrugScreen dottesting=DrugTestUtil.getTestByTypeAndActAppId("D",appAct.getAppactid());
	    ChDrugScreen alcoholtesting=DrugTestUtil.getTestByTypeAndActAppId("A",appAct.getAppactid());
	    
	    standardpanel.setResults(request.getParameter("standardpanel"));
	    standardpanel.setModdate(now);
	    DBUtil.update(standardpanel);
	    
	    dottesting.setResults(request.getParameter("dottesting"));
	    dottesting.setModdate(now);
	    DBUtil.update(dottesting);
	    
	    alcoholtesting.setResults(request.getParameter("alcoholtesting"));
	    alcoholtesting.setModdate(now);
	    DBUtil.update(alcoholtesting);
	    
	    application.setModdate(now);
	    String appActStatus="I";
	    if(standardpanel.getResults().equals("F")||dottesting.getResults().equals("F")||alcoholtesting.getResults().equals("F")){
	    appActStatus = "F";
	    }else if(standardpanel.getResults().equals("P")&&dottesting.getResults().equals("P")&&alcoholtesting.getResults().equals("P")){
	    appActStatus = "P";	
	    }else{
	    appActStatus = "I";	
	    }
		appAct.setActstatus(appActStatus);
		DBUtil.update(appAct);
	    
		
		if (appActStatus.equalsIgnoreCase("F")) {
			application.setAppstatus("F");
			sendTo = "Rejection.jsp";
		}
		DBUtil.update(application);

		session.setAttribute("application", application);
		request.getRequestDispatcher(sendTo).forward(request, response);
	}

}
