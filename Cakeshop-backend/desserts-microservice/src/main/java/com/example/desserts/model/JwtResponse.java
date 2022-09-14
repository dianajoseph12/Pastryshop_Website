package com.example.desserts.model;

public class JwtResponse {
	public String userid;
	public String username;
	public boolean isValid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public JwtResponse(String userid, String username, boolean isValid) {
		super();
		this.userid = userid;
		this.username = username;
		this.isValid = isValid;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
