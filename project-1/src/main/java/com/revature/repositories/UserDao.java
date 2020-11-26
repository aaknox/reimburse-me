package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	// CRUD NETHODS ONLY
	// create
	public void insertUser(User user);

	// read
	public List<User> selectAllUsers();

	public User selectUserByUsername(String username);
	public User selectUserByUserId(int userId);
	

	// update
	public void updateUser(User user);

	public void updatePassword(String username, String password);

	// delete
	public void deleteUser(User user);
}
