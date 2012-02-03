package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.pec.log.LogFactory;

public class LoginDAO {
	
	private static Logger logger = LogFactory.getLogger();
	public boolean login(String username, String pwd) throws Exception{
		boolean result =false;
		
		Connection con =ConnectionManager.getDbConnection();
		Statement stmt =con.createStatement();
		ResultSet results =stmt.executeQuery("select * from user where username='" + username + "' and password ='" + pwd +"'");
		//ResultSet results =stmt.executeQuery("select * from user where username='" + username + "'");
		while(results.next()){
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		LoginDAO dao = new LoginDAO();
		boolean result =dao.login("harinath","secret");
		
		logger.info("user authentication status  " + result);
	}

	public boolean resetPassword(String userName, String password) {
		
		return true;
	}

}
