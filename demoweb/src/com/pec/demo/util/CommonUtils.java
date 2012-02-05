/**
 * 
 */
package com.pec.demo.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author harinath
 *
 */
public class CommonUtils {
	
	/**
	 * Get the MD5 hash of the given password. This is to make sure no one else other than the user knows the password,
	 * even if the physical system is compromised. 
	 * @param password the plain text password.
	 * @return the encrypted password
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getEncryptedPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		byte[] bytesOfMessage = password.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(bytesOfMessage);
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		return hashtext;
	}
	
	public static Date getDateFromString(String dateString) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		return new java.sql.Date(format.parse(dateString).getTime());
		
	}
	

}
