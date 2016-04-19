

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
		case "2": sendTo = "DisplayWorkHistoryServlet";
		break;
		case "3": sendTo = "DisplayEducationServlet";
		break;
		case "4": sendTo = "DisplayDrugScreenServlet";
		break;
		case "5": sendTo = "DisplayVeteranStatusServlet";
		break;
		case "6": sendTo = "DisplayHumanResourceInterviewServlet";
		break;
		case "7": sendTo = "DisplayHiringManagerInterviewServlet";
		break;
		case "8": sendTo = "DisplayGroupInterviewServlet";
		break;
		case "9": sendTo = "DisplayCodingTestServlet";
		break;
		case "10": sendTo = "DisplayApprovalServlet";
		break;
		
		}
		request.getRequestDispatcher(sendTo).forward(request, response);
	}

}
