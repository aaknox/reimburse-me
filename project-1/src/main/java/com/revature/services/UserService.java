package com.revature.services;

import java.util.List;

import com.revature.beans.UserBean;
import com.revature.models.User;


public interface UserService {
	public void addUser(User u);
	
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	
	public void modifyUser(User u);
	
	public void deleteUser(User u);
	
	public User confirmLogin(String username, String password);
	public void modifyPassword(String username, String password);
	public UserBean convertToUserBean(User u);
	public User convertToUser(UserBean b);
}
