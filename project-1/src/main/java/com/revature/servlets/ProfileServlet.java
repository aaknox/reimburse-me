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

public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ProfileServlet.class);
	private static UserServiceImpl userService = new UserServiceImpl();

	public ProfileServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// We will handle the parameters from home.html (currentUser)
		String username = (String) session.getAttribute("username");
		log.info("User attempeted to view profile. Username: " + username);
		User e = userService.getUserByUsername(username);

		if (e != null) {
			RequestDispatcher rd = req.getRequestDispatcher("profile.html");// we want to send our user to the profile
																			// page!
			rd.forward(req, resp);
			log.info(username + " has successfully been sent to their personal profile page.");
		} else {
			PrintWriter pw = HtmlTemplate.getHtmlWriter(resp);
			log.info("No profile found for " + username);
			// we will create an html page on the fly
			pw.println("<h3 style='color:red'>Page Not Found</h3>");
			pw.println("<p>No profile found for current user</p>");
			HtmlTemplate.goBack(pw);
		}
	}
}
