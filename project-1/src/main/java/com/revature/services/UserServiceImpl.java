package com.revature.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.models.UserRole;
import com.revature.repositories.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private static UserDaoImpl userDao = new UserDaoImpl();
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public void addUser(User u) {
		try {
			userDao.insertUser(u);
			log.info("Addition successful");
		} catch (Exception e) {
			log.warn("Error in addUser. Stack Trace: ", e);
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		try {
			list = userDao.selectAllUsers();
			log.info("Mass retrieval of users successful");
		} catch (Exception e) {
			log.warn("Error in getAllUsers. Stack Trace: ", e);
		}
		return list;
	}
	
	@Override
	public List<User> getAllEmployees() {
		List<User> list = new ArrayList<User>();
		try {
			list = userDao.selectAllEmployees();
			log.info("Mass retrieval of employees successful");
		} catch (Exception e) {
			log.warn("Error in getAllEmployees. Stack Trace: ", e);
		}
		return list;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		try {
			user = userDao.selectUserByUsername(username);
			
			log.info("Retrieval successful");
		} catch (Exception e) {
			log.warn("Error in getUserByUsername. Stack Trace: ", e);
		}
		return user;
	}
	
	@Override
	public User getUserByUserId(int userId) {
		User user = null;
		try {
			user = userDao.selectUserByUserId(userId);
			
			log.info("Retrieval successful");
		} catch (Exception e) {
			log.warn("Error in getUserByUserId. Stack Trace: ", e);
		}
		return user;
	}

	@Override
	public User confirmLogin(String username, String password) {
		try {
			log.info("Validating credentials..." + username + " " + password);
			User user = userDao.selectUserByUsername(username);
			log.debug("User found: " + user);
			if (user.getPassword().equals(password)) {
				return user;
			}
		} catch (Exception e) {
			log.warn("Error in confirmLogin. Stack Trace: ", e);
		}
		return null;
	}

	@Override
	public void modifyUser(User u) {
		try {
			userDao.updateUser(u);
			log.info("Update successful");
		} catch (Exception e) {
			log.warn("Error in updateUser. Stack Trace: ", e);
		}
	}

	@Override
	public void modifyPassword(String username, String password) {
		try {
			userDao.updatePassword(username, password);
			log.info("Update password successful");
		} catch (Exception e) {
			log.warn("Error in updatePassword. Stack Trace: ", e);
		}
	}

	@Override
	public void deleteUser(User u) {
		try {
			userDao.deleteUser(u);
			log.info("Update successful");
		} catch (Exception e) {
			log.warn("Error in deleteUser. Stack Trace: ", e);
		}
	}
	
	public UserDTO convertToDTO(User e) {
		return new UserDTO(
				e.getUserId(),
				e.getUsername(),
				e.getPassword(),
				e.getFirstName(),
				e.getLastName(), 
				e.getEmail(),
				e.getHireDate().toString(), 
				e.getUserRole().getRoleId(), 
				e.getUserRole().getRoleName()
				);
	}
	
	public User convertToUser(UserDTO dto) {
		return new User(
				dto.getUserId(),
				dto.getUsername(),
				dto.getPassword(),
				dto.getFirstName(),
				dto.getLastName(), 
				dto.getEmail(),
				LocalDate.parse(dto.getHireDate()), 
				new UserRole(dto.getUserRoleId(), dto.getUserRoleName())
				);
	}

	

}
