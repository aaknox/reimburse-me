package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.services.UserServiceImpl;
import com.revature.util.HtmlTemplate;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	private static UserServiceImpl userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//send user to index.html page
		req.getRequestDispatcher("index.html").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get parameters from index.html
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log.info("User attempted to login with: username - " + username + ", password - " + password);

		// call service layer to handle the business logic for this request
		User user = userService.confirmLogin(username, password);

		if (user != null) {
			// create session with parameters
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);

			// pass the baton to the request dispatcher to the next servlet
			// by forwarding
			// in our case, if confirmation passed -> we want to send our user to the home
			// page!

			// step 0: log message
			log.info(username + " has sucessfully logged on. Going to home page.");
			// step 1: get url path to next servlet
			RequestDispatcher rd = request.getRequestDispatcher("home.html");
			// step 2: forward all web resources to next servlet
			rd.forward(request, response);
		} else {
			// means user was not found during service business logic
			// in our case, we will be show failure page if login unsuccessful

			// establish PrintWriter object to write custom html (using util class)
			// as the response

			// step 0: log message
			log.warn(username + " has failed to login.");
			// step 1: establish print writer from HTML template utility class
			PrintWriter pw = HtmlTemplate.getHtmlWriter(response);
			pw.println("<h3 style='color:red'>Denied</h3>");
			pw.println("<p>Username and/or password was incorrect</p>");
		}
	}
}
