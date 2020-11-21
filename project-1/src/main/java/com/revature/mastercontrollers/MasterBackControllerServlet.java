package com.revature.mastercontrollers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.util.RequestHelper;

/**
 * MasterBackControllerServlet = handles all RESPONSES from server to client
 */

/*
 * Response List: EMPLOYEES: - login pass --> 200 fail --> 403 - logout pass -->
 * 200 fail --> 407 - home page (employee/manager) pass --> 200 fail --> 401 -
 * submit reimbursement request pass --> 201 fail --> 400 - view their own
 * pending requests pass --> 200 fail --> 204 - view their own resolved requests
 * pass --> 200 fail --> 204 - view profile pass --> 200 fail --> 403 - update
 * profile pass --> 200 fail --> 400
 * 
 * MANAGERS - same as EMPLOYEE plus... - approve/deny pending requests pass -->
 * 200 fail --> 400 - view pending requests from all employees pass --> 200 fail
 * --> 400 - view images of receipts from reimbursement requests pass --> 200
 * fail --> 400 - view resolved requests of all employees pass --> 200 fail -->
 * 400 - view all employees pass --> 200 fail --> 400 - view requests from a
 * single employee (by empId) pass --> 200 fail --> 400
 */
public class MasterBackControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MasterBackControllerServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Inside MasterBackControllerServlet...goGet...URL: " + request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// pass the baton to request helper
		log.info("Inside of MasterBackControllerServlet...goPost...");

		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request, response);
	}

}
