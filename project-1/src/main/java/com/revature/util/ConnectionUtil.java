package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	private static Logger log = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() {
		// connects JDBC to servlets
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			log.warn("Cannot load database driver. Stack Trace: ", e);
		}

		// get credentials from connection.properties file
		Properties props = new Properties();

		// NOTE: This isn't always necessary but this object will search for
		// files within our current project - makes it a lot easier to specify where
		// it's located
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Connection conn = null;

		try {
			props.load(loader.getResourceAsStream("connection.properties"));
			String url = props.getProperty("url");
			String dbUsername = props.getProperty("username");
			String dbPassword = props.getProperty("password");

			// establish a connection
			try {
				conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			} catch (SQLException e) {
				log.warn("Unable to obtain connection to the database", e);
			}
		} catch (IOException ex) {
			log.warn("Could not read properties file", ex);
		}

		// return the new connection
		return conn;
	}
}
