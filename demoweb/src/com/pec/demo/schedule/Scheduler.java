package com.pec.demo.schedule;



import java.util.Date;
import java.util.List;

import org.quartz.*;
import org.slf4j.*;

import com.pec.demo.dao.SchedulerDAO;
import com.pec.demo.mail.EmailSender;
import com.pec.demo.model.Mail;

public class Scheduler  implements Job {

	SchedulerDAO dao = new SchedulerDAO();
	public void execute(JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException {
		
		List<Integer> mailIds = dao.getCurrentSchedules();
		
		for (Integer id : mailIds) {
			
			Mail mail = dao.getEmailById(id);			
			EmailSender.sendMailWithSSL(mail.getSubject(), mail.getMessage(),mail.getToEmail());
		}
		
	}
  
	
}
