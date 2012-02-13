package com.pec.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.pec.demo.dao.LoginDAO;
import com.pec.demo.exceptions.ApplicationException;
import com.pec.demo.mail.EmailSender;
import com.pec.demo.mail.EmailTemplates;
import com.pec.demo.mail.Template;
import com.pec.demo.model.User;
import com.pec.log.LogFactory;

public class LoginService {
	private static Logger logger = LogFactory.getLogger();
	private LoginDAO dao = new LoginDAO();
	
	
	public boolean login(String userName, String password) throws ApplicationException{
		try {
			return dao.login(userName, password);
		} catch (Exception e) {
			logger.error("unable to login for user " + userName , e);
			throw new ApplicationException("Unable to login with user name" + userName);
		
		}
	}
	public boolean resetPassword(
			String userName,
			String password,
			String confirmPassword){
		 boolean result = false;
		 if(password.equals(confirmPassword)){
			 result =dao.resetPassword(userName, password);
		 }
		 return result;
	}
	

	public void sendPasswordDetails(String username) throws ApplicationException{
		User user =dao.getUserByUserName(username);
		if(user != null){
			String activationCode =RandomStringUtils.random(5,true, true);
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(EmailTemplates.activationCode,activationCode);
			valuesMap.put(EmailTemplates.firstName, user.getFirstName());
			valuesMap.put(EmailTemplates.userName, user.getUsername());
			String emailBody =EmailTemplates.getEmailMessage(Template.ForgotPassword,valuesMap );
			EmailSender.sendMailWithSSL("Password activation link", emailBody , user.getEmailId());
		}else{
			throw new ApplicationException("No valid user found");
		}
			
		
		
		
	}

}
