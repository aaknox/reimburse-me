package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.repositories.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private static UserDaoImpl userDao = new UserDaoImpl();
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public void addUser(User u) {
		try {
			userDao.setUp();
			userDao.insertUser(u);
			log.info("Addition successful");
		} catch (Exception e) {
			log.warn("Error in addUser. Stack Trace: ", e);
		}
		userDao.exit();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		try {
			userDao.setUp();
			list = userDao.selectAllUsers();
			log.info("Addition successful");
		} catch (Exception e) {
			log.warn("Error in getAllUsers. Stack Trace: ", e);
		}
		userDao.exit();
		return list;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		try {
			userDao.setUp();
			user = userDao.selectUserByUsername(username);
			log.info("Addition successful");
		} catch (Exception e) {
			log.warn("Error in getUserByUsername. Stack Trace: ", e);
		}
		userDao.exit();
		return user;
	}

	@Override
	public User confirmLogin(String username, String password) {
		try {
			userDao.setUp();
			log.info("Validating credentials..." + username + " " + password);
			User user = userDao.selectUserByUsername(username);
			log.debug("User found: " + user);
			if (user.getPassword().equals(password)) {
				return user;
			}
		} catch (Exception e) {
			log.warn("Error in confirmLogin. Stack Trace: ", e);
		}
		userDao.exit();
		return null;
	}

	@Override
	public void modifyUser(User u) {
		try {
			userDao.setUp();
			userDao.updateUser(u);
			log.info("Update successful");
		} catch (Exception e) {
			log.warn("Error in updateUser. Stack Trace: ", e);
		}
		userDao.exit();
	}

	@Override
	public void modifyPassword(String username, String password) {
		try {
			userDao.setUp();
			userDao.updatePassword(username, password);
			log.info("Update password successful");
		} catch (Exception e) {
			log.warn("Error in updatePassword. Stack Trace: ", e);
		}
		userDao.exit();
	}

	@Override
	public void deleteUser(User u) {
		try {
			userDao.setUp();
			userDao.deleteUser(u);
			log.info("Update successful");
		} catch (Exception e) {
			log.warn("Error in deleteUser. Stack Trace: ", e);
		}
		userDao.exit();
	}

}
