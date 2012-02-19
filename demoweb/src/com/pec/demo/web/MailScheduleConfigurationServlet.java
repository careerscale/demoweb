package com.pec.demo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pec.demo.exceptions.ApplicationException;
import com.pec.demo.model.Mail;
import com.pec.demo.service.MailService;

/**
 * Servlet implementation class MailScheduleConfigurationServlet
 */
public class MailScheduleConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MailService service = new MailService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailScheduleConfigurationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/mail.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String toEmail = request.getParameter("toEmail");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		
		//TODO  validations
		
		//let us call MailService that calls DAO to insert into DB
		
		Mail mail = new Mail(toEmail, subject, message);
		
		try {
			service.createMail(mail);
		} catch (ApplicationException e) {
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/mail.jsp");
			dispatcher.forward(request, response);
			return;
			
		}
		
		//request.setAttribute("message", e.getMessage());
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	
	
		
		
		
		
	}

}
