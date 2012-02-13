package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.pec.demo.model.User;
import com.pec.demo.util.CommonUtils;
import com.pec.log.LogFactory;

public class RegistrationDAO {

	private static Logger logger = LogFactory.getLogger();
	Connection con = null;

	public boolean login(String username, String pwd) throws Exception {
		boolean result = false;

		con = ConnectionManager.getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet results = stmt
				.executeQuery("select * from user where username='" + username
						+ "' and password ='" + pwd + "'");	
		while (results.next()) {
			result = true;
		}
		return result;
	}

	public boolean register(User user) throws Exception {
		con = ConnectionManager.getDbConnection();
		PreparedStatement stmt = con
				.prepareStatement("insert into user(username, password, first_name, last_name,email_id,dob,state_id,status_id) values(?,?,?,?,?,?,?,1)");
		stmt.setString(1, user.getUsername());
		stmt.setString(2, CommonUtils.getEncryptedPassword(user.getPassword()));
		stmt.setString(3, user.getFirstName());
		stmt.setString(4, user.getLastName());
		stmt.setString(5, user.getEmailId());
		stmt.setDate(6, CommonUtils.getDateFromString(user.getDob()));
		stmt.setInt(7, Integer.parseInt(user.getState()));

		return stmt.execute();

	}

}
