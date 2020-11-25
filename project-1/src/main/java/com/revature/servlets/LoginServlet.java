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
	
    public LoginServlet() {
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("inside of doPost of LoginServlet...");
		// We will handle the parameters from index.html
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		log.info("User attempeted to login with username " + username);
		User e = userService.confirmLogin(username, password);

		if (e != null) {
			HttpSession session = req.getSession();

			session.setAttribute("username", username);
			RequestDispatcher rd = req.getRequestDispatcher("home.html");// we want to send our user to the home
																				// page!
			rd.forward(req, resp);
			log.info(username + " has successfully logged on");
		} else {
			PrintWriter pw = HtmlTemplate.getHtmlWriter(resp);
			log.info(username + " has failed to login");
			// we will create an html page on the fly
			pw.println("<h3 style='color:red'>Denied</h3>");
			pw.println("<p>Username or password is incorrect</p>");
		}
		log.info("leaving login servlet!");
	}
}
