package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	private static UserServiceImpl userService = new UserServiceImpl();
	//private static ObjectMapper om = new ObjectMapper();

	public LoginServlet() {
		super();
	}

	@Override
	public void init() {
		log.info("Starting Login Servlet...");
	}

	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// route guarding: checking form methods
		boolean isPost = request.getMethod().equalsIgnoreCase("POST");
		if (!isPost) {
			log.warn("invalid method. redirecting to index.html");
			response.sendRedirect("index.html");
		}

		// extract data from submitted login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// check to see if the user has correct username and password
		log.info("Credentials entered: \nUsername: " + username + "\nPassword: " + password);
		User dbUser = userService.confirmLogin(username, password);
		boolean isMatchingDB = false;
		
		if (dbUser.getUsername().equals(username) 
		&& dbUser.getPassword().equals(password)) {
			isMatchingDB = true;
		}
		
		log.info("User credentials entered matches database: " + isMatchingDB);
		if (!isMatchingDB) {
			log.info("going to error page...");
			// meaning login mismatch --> send them a 400 BAD REQUEST status code
			response.sendRedirect("error.html");
		} else {
			log.info("going to home page...");
			log.info("Session user being assigned as: " + username);
			request.getSession().setAttribute("loggedusername", username);
			log.info("New URI: " + "/reimburse-me/home");
			response.sendRedirect("home.html");
		}
	}

	public static void processHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("home.html");
	}
}
