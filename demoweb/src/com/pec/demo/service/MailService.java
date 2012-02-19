package com.pec.demo.service;

import com.pec.demo.dao.SchedulerDAO;
import com.pec.demo.exceptions.ApplicationException;
import com.pec.demo.model.Mail;

public class MailService {
	
	SchedulerDAO dao = new SchedulerDAO();

	public void createMail(Mail mail) throws ApplicationException {
		
		dao.createMail(mail);
		
	}

}
