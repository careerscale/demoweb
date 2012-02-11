package com.pec.demo.mail;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

public class EmailTemplates {
	
	private static String registrationTemplate ="Dear ${firstName}, \n Your  registration with "+
	"DemoWeb application is complete. \n Here are your login details:\n"+
	"username:  ${username} \n" +
	"Password:  ${password} \n" +
	"Thank you for registering with our site, Please login and starting using our website\n"+
	"Regards, \n"+
	"Administrator\n"+
	"DemoWeb\n"+
	"India\n" +
	"http://localhost:8080/demoweb";	
	
	private static String forgotPasswordTemplate ="Dear ${firstName}, \n We have received your forgot password request." +
			"Please click the link below to reset your password \n"+
			"http://localhost:8080/demoweb/forgotPassword?username=${username}&activationCode=${activationCode}"+
			"\nRegards, \n"+
			"Administrator\n"+
			"DemoWeb\n"+
			"India\n" +
			"http://localhost:8080/demoweb";
	
	public static final String firstName ="firstName";
	public static final String userName="username";
	public static final String password ="password";
	public static final String activationCode ="activationCode";
	
	public static String getEmailMessage(Template template, Map<String,String> valuesMap){
		
		
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String resolvedString =null;
		switch(template){
		case Registration:
			resolvedString = sub.replace(registrationTemplate);
			break;
		case ForgotPassword:
			resolvedString = sub.replace(forgotPasswordTemplate);
			break;
		default:
			//Unsupported template
		}
		 		
		return resolvedString;
		
	}

}
