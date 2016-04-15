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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date now = new Date();
		
		long jobtype = Long.parseLong(request.getParameter("jobType"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String birthday = request.getParameter("birthday");
		/*
		Date birthday = new Date();
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		String education = request.getParameter("education");
		String jobHistory = request.getParameter("jobHistory");
		String references = request.getParameter("references");
		String drugUse = request.getParameter("drugUse");
		String veteran = request.getParameter("veteran");
		String citizen = request.getParameter("citizen");
		
		ChApplication newApp = new ChApplication();
		newApp.setChJobtype(DBUtil.getJobtypeByID(jobtype));
		newApp.setName(name);
		newApp.setAddress(address);
		newApp.setBirthday(birthday);
		newApp.setEducation(education);
		newApp.setJobhistory(jobHistory);
		newApp.setDruguse(drugUse);
		newApp.setVeteran(veteran);
		newApp.setCitizen(citizen);
		newApp.setCreatedate(now);
		newApp.setModdate(now);
		
		DBUtil.insertApplication(newApp);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
