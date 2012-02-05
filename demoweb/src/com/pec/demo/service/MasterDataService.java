package com.pec.demo.service;

import java.util.Map;

import com.pec.demo.dao.CommonDAO;

public class MasterDataService {
	
	public static Map<Integer, String> getStates(){
		try {
			return CommonDAO.getStates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
