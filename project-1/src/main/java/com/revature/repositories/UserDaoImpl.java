package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

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
	}

	@Override
	public List<User> selectAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			userList = session.createQuery("FROM User ORDER BY userId", User.class).getResultList();

		} catch (Exception e) {
			log.warn("Failed to find all users in database. Stack Trace: ", e);
		}
		return userList;
	}
	
	@Override
	public List<User> selectAllEmployees() {
		List<User> userList = session.createNativeQuery("Select * from ers_users where ers_user_role_id = 1", User.class).list();
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
		return user;
	}
	
	@Override
	public User selectUserByUserId(int userId) {
		User user = null;

		try {
			List<User> userList = session.createQuery("from User where userId='" + userId + "'", User.class).list();
			user = userList.get(0);
			log.info("My user: " + user);
		} catch (Exception e) {
			log.warn("Failed to find user by username in database. Stack Trace: ", e);
		}
		return user;
	}

	@Override
	public void updateUser(User user) {

		session.beginTransaction();
		try {
			session.merge(user);
			session.update(user);
		} catch (Exception e) {
			log.warn("Failed to update user in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
	}

	@Override
	public void updatePassword(String username, String password) {
		User user = null;

		session.beginTransaction();
		try {
			List<User> list = session.createQuery("from User where username='" + username + "'", User.class).list();

			if (list.size() == 0) {
				log.warn("PANIC!");
				throw new NullPointerException();
			} else {
				user = list.get(0);
				if (user != null) {
					// update password
					user.setPassword(password);
					session.update(user);
					log.info("Password has been updated!");
				}
			}

		} catch (Exception e) {
			log.warn("Failed to update user password in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
	}

	@Override
	public void deleteUser(User user) {

		session.beginTransaction();
		try {
			log.info("removing user, " + user);
			session.delete(user);
			log.info("Removal of user was successful.");
		} catch (Exception e) {
			log.warn("Failed to remove user from database. Stack Trace: ", e);
		}

		session.getTransaction().commit();

	}

}
