package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	private static Session session = HibernateUtil.getSession();

	@Override
	public void insertUser(User user) {
		session.beginTransaction();

		try {
			session.save(user);
		} catch (Exception e) {
			log.warn("Failed to insert user into database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public List<User> selectAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			userList = session.createQuery("SELECT * FROM User ORDER BY userId", User.class).getResultList();

		} catch (Exception e) {
			log.warn("Failed to find all users in database. Stack Trace: ", e);
		}
		session.close();
		return userList;
	}

	@Override
	public User selectUserByUsername(String username) {
		User user = null;
		try {
			List<User> userList = session.createQuery("from User where username='" + username + "'", User.class).list();
			user = userList.get(0);
			log.info("My user: " + user);
		} catch (Exception e) {
			log.warn("Failed to find user by username in database. Stack Trace: ", e);
		}
		session.close();
		return user;
	}

	@Override
	public void updateUser(User user) {

		session.beginTransaction();
		try {
			session.update(user);
		} catch (Exception e) {
			log.warn("Failed to update user in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updatePassword(String username, String password) {
		User user = null;
		session.beginTransaction();
		try {
			user = session.get(User.class, username);
			if (user != null) {
				// update password
				user.setPassword(password);
				session.update(user);
			}
		} catch (Exception e) {
			log.warn("Failed to update user by id in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteUser(User user) {

		session.beginTransaction();
		try {
			log.info("removing user, " + user);
			session.delete(user);
		} catch (Exception e) {
			log.warn("Failed to update user by id in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
		session.close();

	}

}
