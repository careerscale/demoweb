package com.pec.demo.mail;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
	
	private static final List<String> PLACE_HOLDERS = new ArrayList<String>();
	static{
		PLACE_HOLDERS.add("$$firstName$$");
		PLACE_HOLDERS.add("$$email$$");
		PLACE_HOLDERS.add("$$dob$$");
		
	}
		

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
