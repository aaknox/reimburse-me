package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.util.HtmlTemplate;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LogoutServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("User attempted to log out.");
		// invalidate session user
		HttpSession session = request.getSession();
		session.invalidate();
		// show user successful log out page
		PrintWriter pw = HtmlTemplate.getHtmlWriter(response);
		response.setContentType("text/html");
		pw.println("<html><head><title>Log Out</title></head>" + "<body>" + "<h1>You have successfully logged out!</h1>"
				+ "<a href='login'>Return to Login</a>" + "</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
