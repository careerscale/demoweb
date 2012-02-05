package com.pec.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pec.demo.dao.RegistrationDAO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("states", MasterDataService.getStates());
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		
		if(!password.equals(confirmPassword)){
			//throw error and respond with same register.jsp with values
		}
		RegistrationDAO regDAO = new RegistrationDAO();
		boolean result;
		try{
		result =regDAO.register(firstName, lastName, userName,password,email, dob);
		if(result){
		 	writer.write("Registration is successful");
		}
		
		}catch (Exception e) {
			logger.error("Error while registration", e);
			//show error on page
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
	
		}
		
	}

}
