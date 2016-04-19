

import java.io.IOException;
import java.util.Date;

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

/**
 * Servlet implementation class UpdateVeteranStatusServlet
 */
@WebServlet("/UpdateVeteranStatusServlet")
public class UpdateVeteranStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVeteranStatusServlet() {
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
		String sendTo = "ApplicationActivityListServlet";
		Date now = new Date();
		ChApplication application = (ChApplication) session.getAttribute("application");
		
		String appActStatus = request.getParameter("status");
		ChApplicationActivity appAct = DBUtil.getApplicationActivityByID(application.getAppid(), 5);
		
		appAct.setActmoddate(now);
		appAct.setActstatus(appActStatus);
		DBUtil.update(appAct);
		
		String alteredComment = request.getParameter("veterancomment");
		ChComment currentComment = DBUtil.getCommentByAppActId(appAct.getAppactid());
		
		currentComment.setComments(alteredComment);
		currentComment.setModdate(now);
		DBUtil.update(currentComment);
		
		application.setModdate(now);
		if(appActStatus.equalsIgnoreCase("F")) {
			application.setAppstatus("F");
			sendTo = "Rejection.jsp";
		}
		DBUtil.update(application);
		
		session.setAttribute("application", application);
		request.getRequestDispatcher(sendTo).forward(request, response);
	}

}
