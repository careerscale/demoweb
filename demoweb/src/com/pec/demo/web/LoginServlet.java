package com.pec.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pec.demo.dao.LoginDAO;
import com.pec.log.LogFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogFactory.getLogger();
    /**
     * Default constructor. 
     */
    public LoginServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.write("Hello" + request.getParameter("first"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =request.getParameter("userId");
		
		String password = request.getParameter("password");
		LoginDAO dao = new LoginDAO();
		boolean loginStatus = false;
		try {
			loginStatus = dao.login(username, password);
		} catch (Exception e) {
			logger.error("Error while login ", e);
		}
		
		PrintWriter writer = response.getWriter();
		writer.write("Login status is " + loginStatus);
		
	}

}
