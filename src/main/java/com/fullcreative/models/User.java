package com.fullcreative.models;

public class User {
	private String username;
	private String email;
	private String password;

	public User() {

	}

	public User(String userName, String email, String password) {
		super();
		this.username = userName;
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", email=" + email + ", password=" + password + "]";
	}

}