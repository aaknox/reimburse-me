package com.revature.mastercontrollers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MasterFrontControllerServlet = handles all REQUESTS from client to server
 */

/*
 * Request List:
 * 	EMPLOYEES: 
 * - login
 * - logout
 * - home page (employee/manager)
 * - submit reimbursement request
 * - view their own pending requests
 * - view their own resolved requests
 * - view profile
 * - update profile
 * 
 *  MANAGERS
 *  - same as EMPLOYEE plus...
 *  - approve/deny pending requests
 *  - view pending requests from all employees
 *  - view images of receipts from reimbursement requests
 *  - view resolved requests of all employees
 *  - view all employees
 *  - view requests from a single employee (by empId)
 */
public class MasterFrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
