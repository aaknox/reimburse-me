package com.revature.util;

import java.time.LocalDate;

public class RegisterTemplate {
	//fields
	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String email;

	private String hireDate = LocalDate.now().toString();

	private int userRoleId;
	
	private String userRoleName;
	
	public RegisterTemplate() {
		// TODO Auto-generated constructor stub
	}

	public RegisterTemplate(String username, String password, String firstName, String lastName, String email,
			String hireDate, int userRoleId, String userRoleName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.hireDate = hireDate;
		this.userRoleId = userRoleId;
		this.userRoleName = userRoleName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	@Override
	public String toString() {
		return "RegisterTemplate [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", hireDate=" + hireDate + ", userRoleId="
				+ userRoleId + ", userRoleName=" + userRoleName + "]";
	}
}
