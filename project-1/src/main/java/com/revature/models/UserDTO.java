package com.revature.models;

import java.io.Serializable;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userId;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String email;

	private String hireDate;

	// already an int and string
	private int userRoleId;
	private String userRoleName;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int userId, String username, String password, String firstName, String lastName, String email,
			String hireDate, int userRoleId, String userRoleName) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.hireDate = hireDate;
		this.userRoleId = userRoleId;
		this.userRoleName = userRoleName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + userRoleId;
		result = prime * result + ((userRoleName == null) ? 0 : userRoleName.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (userRoleId != other.userRoleId)
			return false;
		if (userRoleName == null) {
			if (other.userRoleName != null)
				return false;
		} else if (!userRoleName.equals(other.userRoleName))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", hireDate=" + hireDate + ", userRoleId="
				+ userRoleId + ", userRoleName=" + userRoleName + "]";
	}

	

}
