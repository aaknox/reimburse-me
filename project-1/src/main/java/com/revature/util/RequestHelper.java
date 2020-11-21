package com.revature.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.servlets.LoginServlet;

public class RequestHelper {
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	
	public static void process(HttpServletRequest request, HttpServletResponse response) 
			throws ERSException, ServletException, IOException {
		System.out.println("In Forwarding request helper: process() -- URI: " + request.getRequestURI());

		switch (request.getRequestURI()) {
		case "/project-1/reimburse-me/login":
			log.info("forwarding login credentials...");
			// goes through login check
			LoginServlet.processLogin(request, response);
			break;
		default:
			System.out.println("default case");
			// incorrect user credentials or bad logs go here
			response.sendRedirect("error.html");
			break;
		}
	}

	public static void retrieve(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("In Forwarding request helper: retrieve() -- URI: " + request.getRequestURI());

		switch (request.getRequestURI()) {
		case "/project-1/reimburse-me/home.html":
			log.info("going to home page...");
			// goes to home page with current session user
			LoginServlet.processHome(request, response);
			break;
		}
	}
}
