package com.revature.util;


public class DeleteTemplate {
	// fields
	private int userId;

	private String username;

	private String firstName;

	private String lastName;

	public DeleteTemplate() {
		// TODO Auto-generated constructor stub
	}

	public DeleteTemplate(int userId, String username, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "DeleteTemplate [userId=" + userId + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
