package com.pec.demo.service;

import com.pec.demo.dao.LoginDAO;

public class LoginService {
	LoginDAO dao = new LoginDAO();
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

}
