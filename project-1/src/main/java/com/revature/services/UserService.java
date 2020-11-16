package com.revature.services;

import java.util.List;

import com.revature.models.User;


public interface UserService {
	public void addUser(User u);
	
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	public User confirmLogin(String username, String password);
	
	public void modifyUser(User u);
	public void modifyPassword(String username, String password);
	
	public void deleteUser(User u);
}
