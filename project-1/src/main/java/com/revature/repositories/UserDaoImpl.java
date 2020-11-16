package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.revature.models.User;

public class UserDaoImpl implements UserDao {
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	// load hibernate session factory
	protected SessionFactory sessionFactory;

	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		// HERE, you can configure settings from hibernate.cfg.xml
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			log.warn("failed here. Stack Trace: ", e);
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public void exit() {
		sessionFactory.close();
	}

	@Override
	public void insertUser(User user) {
		Session session = sessionFactory.openSession();

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
		Session session = sessionFactory.openSession();
		List<User> userList = new ArrayList<User>();

		session.beginTransaction();
		try {
			userList = session.createQuery("SELECT * FROM ers_users ORDER BY ers_user_id", User.class).getResultList();

		} catch (Exception e) {
			log.warn("Failed to find all users in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
		session.close();
		return userList;
	}

	@Override
	public User selectUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		User user = null;
		session.beginTransaction();
		try {
			Query query = session.createQuery("from ers_users where ers_username =:username ").setParameter("username", username);
			user = (User) query.getSingleResult();
			log.info("My user: " + user);
		} catch (Exception e) {
			log.warn("Failed to find user by username in database. Stack Trace: ", e);
		}

		session.getTransaction().commit();
		session.close();
		return user;
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.openSession();

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
		Session session = sessionFactory.openSession();
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
		Session session = sessionFactory.openSession();

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
