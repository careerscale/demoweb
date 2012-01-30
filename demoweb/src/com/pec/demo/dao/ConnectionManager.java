package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getDbConnection() throws Exception{
		String conString ="jdbc:mysql://localhost:3306/test";
		return DriverManager.getConnection(conString,"root","secret");
		
	}

}
