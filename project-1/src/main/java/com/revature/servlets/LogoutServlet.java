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

public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LogoutServlet.class);
	
	public LogoutServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		if (session != null) {
			String username = (String) session.getAttribute("username");
			log.info(username + " has logged out");
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");
			HtmlTemplate.goBack(pw);
			
			session.invalidate();
			
		}

		resp.setStatus(200);
	}

}
