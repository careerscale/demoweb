package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pec.demo.util.CommonUtils;
import com.pec.log.LogFactory;

public class CommonDAO {

	private static Logger logger = LogFactory.getLogger();
	public static Map<Integer, String> getStates() throws Exception{
	
		Map<Integer, String> states = new HashMap<Integer, String>();
		Connection con =ConnectionManager.getDbConnection();
		Statement stmt =con.createStatement();
		ResultSet results =stmt.executeQuery("select * from state");
		while(results.next()){
			states.put(results.getInt(1), results.getString(2));
		}
		return states;
	}
	
	public static void main(String[] args) {
		try {
			CommonDAO.getStates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
