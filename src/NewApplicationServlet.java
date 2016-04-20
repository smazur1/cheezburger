/*
 * Eric made this
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.ChApplication;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class NewApplicationServlet
 */
@WebServlet("/NewApplicationServlet")
public class NewApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewApplicationServlet() {
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
		String sendTo = "index.jsp";
		boolean doInsert = true;
		Date now = new Date();
		
		long jobtype = Long.parseLong(request.getParameter("jobType"));
		String name = request.getParameter("name");
		if(name.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("namerror", "Please type your name.");
			doInsert = false;
		} else {
			request.setAttribute("namdef", name);
		}
		String address = request.getParameter("address");
		if(address.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("adderror", "Please type your address.");
			doInsert = false;
		} else {
			request.setAttribute("adddef", address);
		}
		String birthday = request.getParameter("birthday");
		if(birthday.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("doberror", "Please type your date of birth.");
			doInsert = false;
		} else {
			request.setAttribute("dobdef", birthday);
		}
		/*
		Date birthday = new Date();
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		String education = request.getParameter("education");
		if(education.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("eduerror", "Please describe your education.");
			doInsert = false;
		} else {
			request.setAttribute("edudef", education);
		}
		String jobHistory = request.getParameter("jobHistory");
		if(jobHistory.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("joberror", "Please describe your job history.");
			doInsert = false;
		} else {
			request.setAttribute("jobdef", jobHistory);
		}
		String references = request.getParameter("references");
		if(references.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("referror", "Please list some references or type none.");
			doInsert = false;
		} else {
			request.setAttribute("refdef", references);
		}
		String drugUse = request.getParameter("drugUse");
		if(drugUse.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("druerror", "Please describe your drug use or lack thereof.");
			doInsert = false;
		} else {
			request.setAttribute("drudef", drugUse);
		}
		String veteran = request.getParameter("veteran");
		if(veteran.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("veterror", "Please describe your veteran status.");
			doInsert = false;
		} else {
			request.setAttribute("vetdef", veteran);
		}
		String citizen = request.getParameter("citizen");
		if(citizen.isEmpty()) {
			sendTo = "NewApplicationFormServlet";
			request.setAttribute("naterror", "Please describe your nationality.");
			doInsert = false;
		} else {
			request.setAttribute("natdef", citizen);
		}
		if(doInsert) {
			ChApplication newApp = new ChApplication();
			newApp.setChJobtype(DBUtil.getJobtypeByID(jobtype));
			newApp.setName(name);
			newApp.setAddress(address);
			newApp.setBirthday(birthday);
			newApp.setEducation(education);
			newApp.setJobhistory(jobHistory);
			newApp.setAppref(references);
			newApp.setDruguse(drugUse);
			newApp.setVeteran(veteran);
			newApp.setCitizen(citizen);
			newApp.setCreatedate(now);
			newApp.setModdate(now);
	//		newApp.setAppstatus("I");
			DBUtil.insertApplication(newApp);
		}
		request.getRequestDispatcher(sendTo).forward(request, response);
	}

}
