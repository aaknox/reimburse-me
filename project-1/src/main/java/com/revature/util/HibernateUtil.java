package com.revature.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Logger log = Logger.getLogger(HibernateUtil.class);
	private static Session ses;
	
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public HibernateUtil() {
		
	}
	
	public static Session getSession() {
		log.info("starting hibernate connection session...");
		if(ses==null) {
			ses=sf.openSession();
		}
		return ses;
	}
}
