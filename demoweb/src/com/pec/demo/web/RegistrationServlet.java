package com.pec.demo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pec.demo.dao.RegistrationDAO;
import com.pec.demo.mail.EmailSender;
import com.pec.demo.mail.EmailTemplates;
import com.pec.demo.mail.Template;
import com.pec.demo.service.MasterDataService;
import com.pec.log.LogFactory;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogFactory.getLogger();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("states", MasterDataService.getStates());
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");

		if (!password.equals(confirmPassword)) {
			// throw error and respond with same register.jsp with values
		}
		RegistrationDAO regDAO = new RegistrationDAO();
		boolean result;
		try {
			result = regDAO.register(firstName, lastName, userName, password,
					email, dob);
			// writer.write("Registration is successful");
			sendRegistrationEmail(email, userName, firstName, password);
			HttpSession session = request.getSession();
			session.setAttribute("username", userName);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			logger.error("Error while registration", e);
			// show error on page
			request.setAttribute("error", e.getMessage());
			request.setAttribute("states", MasterDataService.getStates());
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);

		}

	}

	/**
	 * Sends out email on successful registration
	 * 
	 * @param email
	 *            the email id of the user.
	 * @param userName
	 *            the user name.
	 * @param firstName
	 *            first name of the user.
	 * @param password
	 *            password of the user. Remember password is always stored in
	 *            encrypted format on database. Only at the time of
	 *            registration, password is in plain text.
	 */
	public void sendRegistrationEmail(String email, String userName,
			String firstName, String password) {
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put(EmailTemplates.firstName, firstName);
		valuesMap.put(EmailTemplates.userName, userName);
		valuesMap.put(EmailTemplates.password, password);
		String message = EmailTemplates.getEmailMessage(Template.Registration,
				valuesMap);
		EmailSender.sendMailWithSSL("Registration successful", message, email);

	}

	/**
	 * This method is only to test email functionality to test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RegistrationServlet servlet = new RegistrationServlet();
		servlet.sendRegistrationEmail("harinathreddy@gmail.com", "harinath",
				"hari first", "passwordencrypted");
	}

}
