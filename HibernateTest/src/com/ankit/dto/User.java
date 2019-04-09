package com.ankit.dto;

public class User {

	private Integer userid;

	private String email;
	private String name;
	private String password;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", email=" + email + ", name=" + name
				+ ", password=" + password + "]";
	}
	/**
	 * @param userid
	 * @param email
	 * @param name
	 * @param password
	 */
	public User(Integer userid, String email, String name, String password) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;
		this.password = password;
	}
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	}
