package com.business.loginCredentials;

public class AdminLogin {
	private String email;
	private String password;
	public AdminLogin() {
		super();
		// TODO Auto-generated constructor stub
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
		return "AdminLogin [email=" + email + ", password=" + password + "]";
	}
	
	
}
