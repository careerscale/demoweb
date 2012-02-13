package com.pec.demo.model;

public class User {
	private String username;
	private String password;
	private String emailId;
	private String firstName;
	private String lastName;
	private String dob;
	private String state;
	

	/**
	 * Default constructor
	 */
	@Deprecated
	public User() {

	}

	/**
	 * Constructor for user object
	 * @param username
	 * @param emailId
	 * @param firstName
	 * @param lastName
	 * @param state 
	 * @param dob 
	 */
	public User(String username, String emailId, String firstName,
			String lastName, String dob, String state) {
		this.username = username;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob =dob;
		this.state =state;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}
