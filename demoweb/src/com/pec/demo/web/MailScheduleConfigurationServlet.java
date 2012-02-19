package com.pec.demo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pec.demo.exceptions.ApplicationException;
import com.pec.demo.model.Mail;
import com.pec.demo.model.Schedule;
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
		
		request.setAttribute("minutes", getMinuteInHour());
		request.setAttribute("hours", getHoursInDay());
		request.setAttribute("days", getDaysInMonth());
		request.setAttribute("months", getMonthsInYear());
		
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/mail.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * Get the possible minute values in an hour.
	 * @return
	 */
	private Map<String, String> getMinuteInHour() {
		Map<String, String> valuesMap = new LinkedHashMap<String, String>();
		
		valuesMap.put("*", "Every");
		for(int i=0; i<60 ; i ++){
			valuesMap.put(""+i, ""+i);
		}		
		return valuesMap;	
	}
	private Map<String, String> getHoursInDay() {
		Map<String, String> valuesMap = new LinkedHashMap<String, String>();
		
		valuesMap.put("*", "Every");
		for(int i=0; i< 24 ; i ++){
			valuesMap.put(""+i, ""+i);
		}		
		return valuesMap;	
	}

	private Map<String, String> getDaysInMonth() {
		Map<String, String> valuesMap = new LinkedHashMap<String, String>();
		
		valuesMap.put("*", "Every");
		for(int i=0; i<=31 ; i ++){
			valuesMap.put(""+i, ""+i);
		}		
		return valuesMap;	
	}
	
	
	private Map<String, String> getMonthsInYear() {
		Map<String, String> valuesMap = new LinkedHashMap<String, String>();
		
		valuesMap.put("*", "Every");
		for(int i=1; i<=12 ; i ++){
			valuesMap.put(""+i, ""+i);
		}		
		return valuesMap;	
	}
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String toEmail = request.getParameter("toEmail");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		String minutes = request.getParameter("minutes");
		String hours = request.getParameter("hours");
		String days = request.getParameter("days");
		String months = request.getParameter("months");
		
		Schedule schedule = new Schedule(minutes, hours, days, months);
		
		
		//TODO  validations
		
		//let us call MailService that calls DAO to insert into DB
		
		Mail mail = new Mail(toEmail, subject, message);
		mail.setSchedule(schedule);
		try {
			service.createMail(mail);
		} catch (ApplicationException e) {
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/mail.jsp");
			dispatcher.forward(request, response);
			return;
			
		}
		
		request.setAttribute("error", "Mail has been created successfully");
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/mail.jsp");
		dispatcher.forward(request, response);
	
	
		
		
		
		
	}

}
