package com.pec.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pec.demo.dao.LoginDAO;
import com.pec.demo.service.LoginService;
import com.pec.log.LogFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogFactory.getLogger();
	private LoginService service = null;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        service = new LoginService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (request.getParameter("forgot") != null) {
			try {
				service.sendPasswordDetails(username);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
			}
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/forgotpwd.jsp");
			dispatcher.forward(request, response);
			return;

		}

		LoginDAO dao = new LoginDAO();
		boolean loginStatus = false;
		try {
			loginStatus = dao.login(username, password);
		} catch (Exception e) {
			logger.error("Error while login ", e);
		}

		HttpSession session = request.getSession();
		if (loginStatus == true) {
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		} else {
			// session.setAttribute("error",
			// "User name or password is invalid");
			request.setAttribute("error", "User name or password is invalid");
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);

		}
	}

}
