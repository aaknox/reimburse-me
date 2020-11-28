package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.RequestHelper;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// URL Rewriting
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/project-1/", "");
		System.out.println("Inside of FrontController - doPost. Destination: " + URI);
		
		switch(URI) {
		case "login":
			System.out.println("going to login");
			RequestHelper.processLogin(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "user":
			RequestHelper.processProfile(request, response);
			break;
		case "reimbursement/submit":
			System.out.println("submitting a reimbursement...");
			RequestHelper.processSubmitReimb(request, response);
			break;
		}
		
		System.out.println("Leaving front controller...");
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		final String URI = request.getRequestURI().replace("/project-1/", "");
		System.out.println("Inside of FrontController - doGet. Destination: " + URI);
		
		switch(URI) {
		case "reimbursements/view-past":
			System.out.println("viewing past reimbursements ...");
			RequestHelper.processViewPastReimb(request, response);
			break;
		}
	}

}
