package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.pec.demo.util.CommonUtils;
import com.pec.log.LogFactory;

public class RegistrationDAO {
	

	private static Logger logger = LogFactory.getLogger();
	Connection con = null;
	public boolean login(String username, String pwd) throws Exception{
		boolean result =false;
		
		con =ConnectionManager.getDbConnection();
		Statement stmt =con.createStatement();
		ResultSet results =stmt.executeQuery("select * from user where username='" + username + "' and password ='" + pwd +"'");
		//ResultSet results =stmt.executeQuery("select * from user where username='" + username + "'");
		while(results.next()){
			result = true;
		}
		return result;
	}
	public boolean register(String firstName, String lastName, String userName,
			String password, String email, String dob) throws Exception {
		con =ConnectionManager.getDbConnection();
		
		PreparedStatement stmt =con.prepareStatement("insert into user(username, password, first_name, last_name,email_id,dob) values(?,?,?,?,?,?)");
		stmt.setString(1, userName);
		stmt.setString(2, CommonUtils.getEncryptedPassword(password));
		stmt.setString(3, firstName);
		stmt.setString(4, lastName);
		stmt.setString(5, email);
		stmt.setDate(6, CommonUtils.getDateFromString(dob));
		
		return stmt.execute();
		/*
		username		 
		password
		first_name
		last_name
		dob
		email_id
		address_line
		profession_id
		state_id
		*/
		
	}
	
	public static void main(String[] args) {
		RegistrationDAO dao = new RegistrationDAO();
		try {
			dao.register("hari1", "mallepally", "harinath1", "test", "hari@harinath.in", "12/10/2012");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
