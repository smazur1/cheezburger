

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.ChApplication;
import model.ChApplicationActivity;
import model.ChUser;

/**
 * Servlet implementation class ApplicationActivityListServlet
 */
@WebServlet("/ApplicationActivityListServlet")
public class ApplicationActivityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationActivityListServlet() {
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
		ChUser current = (ChUser)session.getAttribute("user");
		ChApplication application = (ChApplication) session.getAttribute("application");
		HashMap<ChApplicationActivity, String> accessMap = DBUtil.getAppActAccessMap(current.getChUserRoles().get(0).getChHrrole(), application);
		request.setAttribute("activitymap", accessMap);
		request.getRequestDispatcher("Applicant.jsp").forward(request, response);
	}

}
