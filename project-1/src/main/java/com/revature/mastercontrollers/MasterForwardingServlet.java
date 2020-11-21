package com.revature.mastercontrollers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.util.ERSException;
import com.revature.util.RequestHelper;

/**
 * MasterForwardingServlet = handles all REQUESTS from client to server
 */

/*
 * Request List: EMPLOYEES: - login - logout - home page (employee/manager) -
 * submit reimbursement request - view their own pending requests - view their
 * own resolved requests - view profile - update profile
 * 
 * MANAGERS - same as EMPLOYEE plus... - approve/deny pending requests - view
 * pending requests from all employees - view images of receipts from
 * reimbursement requests - view resolved requests of all employees - view all
 * employees - view requests from a single employee (by empId)
 */
public class MasterForwardingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MasterForwardingServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pass the baton to request helper
		log.info("Inside of Forwarding Master Servlet...goGet...");
		log.info("Request: " + request.toString());
		log.info("Response: " + response.getStatus());
		log.info("URI: " + request.getRequestURI());
		RequestHelper.retrieve(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pass the baton to request helper
		log.info("Inside of Forwarding Master Servlet...goPost...");

		try {
			log.info("Request: " + request.toString());
			log.info("Response: " + response.getStatus());
			log.info("URI: " + request.getRequestURI());
			RequestHelper.process(request, response);
			//request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request, response);
		} catch (ERSException e) {
			log.warn("ForwardingMasterServlet failed here. Stack Trace: ", e);
		}
	}

}
