package com.pec.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pec.demo.dao.RegistrationDAO;
import com.pec.demo.exceptions.ApplicationException;
import com.pec.demo.mail.EmailSender;
import com.pec.demo.mail.EmailTemplates;
import com.pec.demo.mail.Template;
import com.pec.demo.model.User;
import com.pec.log.LogFactory;

public class RegistrationService {
	private RegistrationDAO regDAO = null;
	private static Logger logger = LogFactory.getLogger();

	
	public boolean registerUser(User user) throws ApplicationException{
		
		boolean result =false;
		regDAO = new RegistrationDAO();
		try{
		result = regDAO.register(user);
		}catch(Exception e){
			logger.error("error while registration ", e);
			throw new ApplicationException("Unable to register" + e.getMessage());
		}
		
		// writer.write("Registration is successful");
		sendRegistrationEmail(user.getEmailId(), user.getUsername(), user.getFirstName(), user.getPassword());
	    return result;
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


}
