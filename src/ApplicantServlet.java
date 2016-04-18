

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApplicantServlet
 */
@WebServlet("/ApplicantServlet")
public class ApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantServlet() {
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
		String sendTo = "ApplicationListServlet";
		String actid = request.getParameter("actid");
		
		switch(actid) {
		case "1": sendTo = "DisplayNationalityServlet";
		break;
		case "2": sendTo = "";
		break;
		case "3": sendTo = "";
		break;
		case "4": sendTo = "";
		break;
		case "5": sendTo = "";
		break;
		case "6": sendTo = "";
		break;
		case "7": sendTo = "";
		break;
		case "8": sendTo = "";
		break;
		case "9": sendTo = "";
		break;
		case "10": sendTo = "";
		break;
		
		}
		request.getRequestDispatcher(sendTo).forward(request, response);
	}

}
