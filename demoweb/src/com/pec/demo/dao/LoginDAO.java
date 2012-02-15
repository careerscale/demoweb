package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.pec.demo.model.User;
import com.pec.demo.util.CommonUtils;
import com.pec.log.LogFactory;

public class LoginDAO {
	
	private static Logger logger = LogFactory.getLogger();
	public boolean login(String username, String pwd) throws Exception{
		boolean result =false;
		
		Connection con =ConnectionManager.getDbConnection();
		Statement stmt =con.createStatement();
		ResultSet results =stmt.executeQuery("select * from user where username='" + username + "' and password ='" + CommonUtils.getEncryptedPassword(pwd) +"'");
		//ResultSet results =stmt.executeQuery("select * from user where username='" + username + "'");
		while(results.next()){
			result = true;
		}
		return result;
	}

	public boolean resetPassword(String userName, String password) {
		
		return true;
	}

	public User getUserByUserName(String username) {
		User user = null;
		try{
		Connection con =ConnectionManager.getDbConnection();
		Statement stmt =con.createStatement();
		ResultSet results =stmt.executeQuery("select * from user where username='" + username + "'");
		//ResultSet results =stmt.executeQuery("select * from user where username='" + username + "'");
		
		while(results.next()){
			user = new User(results.getString("username"),results.getString("email_id"),results.getString("first_name"),results.getString("last_name"), null, null);
		}
		}catch(SQLException e){
			logger.error("jdbc exception", e);
			
		} catch (Exception e) {
			
			logger.error("unknown exception", e);
		}
		return user;

		
	}
	
	
	public static void main(String[] args) throws Exception {
		LoginDAO dao = new LoginDAO();
		boolean result =dao.login("harinath","secret");
		
		logger.info("user authentication status  " + result);
	}


}
