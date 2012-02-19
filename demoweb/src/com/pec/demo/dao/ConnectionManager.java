package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pec.log.LogFactory;

public class ConnectionManager {

	private static Logger logger = LogFactory.getLogger();
	private static final String conString = "jdbc:mysql://localhost:3306/demoweb";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cne) {
			logger.error("Unable to load driver", cne);
		}
	}

	public static Connection getDbConnection() throws Exception {
		
		return DriverManager.getConnection(conString, "root", "secret");

	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			logger.error("unable to close connection", e);
		}
		
	}

}
