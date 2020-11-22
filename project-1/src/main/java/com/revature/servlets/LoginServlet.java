package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserServiceImpl;
import com.revature.util.ERSException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	private static UserServiceImpl userService = new UserServiceImpl();
	private static ObjectMapper om = new ObjectMapper();

	@Override
	public void init() {
		log.info("Starting Login Servlet...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Inside of LoginServlet...toPost...");
		try {
			//get form data
			log.info("User saved as: " + req.getParameter("username"));
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			log.info("information received: \nUsername: " + username + "\nPassword: " + password);
			
			//verify credentials
			User temp = userService.confirmLogin(username, password); //returns with user or null value
			//null = redirect to error.html
			//not null = save session and go to home page
			if(temp == null) {
				log.warn("user not found!");
				//convert some data into JSON to send with redirect
				int statusCode = 404;
				String cause = "Invalid Credentials";
				ERSException exception = new ERSException("Username or password was incorrect");
				
				PrintWriter pw = resp.getWriter();
				resp.setContentType("application/json");
				String json = om.writeValueAsString("{'status_code': " + statusCode + "," + 
													"'cause': '" + cause + "'," + 
													"'stack_trace': '" + exception + "'}");
				pw.write(json);
				log.info("Converted JSON: " + json);
				log.debug(resp.toString());
				//send user to new location
				resp.sendRedirect("/project-1/error.html");
			}else {
				log.info("user found!");
				req.getSession().setAttribute("loggeduser", temp.getFirstName());
				resp.sendRedirect("/project-1/home.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
