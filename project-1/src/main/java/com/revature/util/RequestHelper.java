package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.ReimbursementServiceImpl;
import com.revature.services.UserServiceImpl;

public class RequestHelper {

	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	private static UserServiceImpl userService = new UserServiceImpl();
	private static ReimbursementServiceImpl reimbService = new ReimbursementServiceImpl();

	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("inside of request helper...processLogin...");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		System.out.println(body);

		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		log.info("User attempted to login with username " + username);
		User e = userService.confirmLogin(username, password);

		if (e != null) {
			// Let's get the current session or create one if none exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");

			// create an EmployeeDTO from Employee object
			// create a method to create an employee DTO (in EmployeeService class)
			UserDTO eDTO = userService.convertToDTO(e);
			System.out.println(eDTO);

			pw.println(om.writeValueAsString(eDTO));

			log.info(username + " has successfully logged in.");
		} else {
			res.setContentType("application/json");
			res.setStatus(204); // this means that the connection was successful but no user found!
		}
		log.info("leaving request helper now...");
	}

	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false);

		if (session != null) {
			String username = (String) session.getAttribute("username");
			log.info(username + " has logged out.");
			session.invalidate();
		}

		res.setStatus(200);
	}

	public static void processEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");

//		List<User> all = EmployeeService.findAll();
//		List<EmployeeDTO> allDTO = new ArrayList<>();
//		
//		for (Employee e : all) {
//			allDTO.add(EmployeeService.convertToDTO(e));
//		}
//		
//		String json = om.writeValueAsString(allDTO);
//		
//		PrintWriter pw = res.getWriter();
//		pw.println(json);

	}

	public static void processProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("User choose to view their profile.");
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		User profile = userService.getUserByUsername((String) session.getAttribute("username"));
		UserDTO eDTO = userService.convertToDTO(profile);
		System.out.println(eDTO);

		String json = om.writeValueAsString(eDTO);

		pw.println(json);

	}

	public static void processSubmitReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("User choose to submit a reimbursement.");
		System.out.println("Let's get started...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		System.out.println(body);
		// use body to make a reimbTemplate
		ReimbTemplate reimbAttempt = om.readValue(body, ReimbTemplate.class);

			// convert template into POJO
			Reimbursement r = reimbService.convertToReimb(reimbAttempt);
			// insert new reimb into database
			int id = reimbService.addReimbursement(r);
			System.out.println("back in request helper. converting data into json...");
			Reimbursement result = reimbService.getReimbursementById(id);
			
			//check that we got a new reimbursement in database
			if (result != null) {
				ReimbursementDTO reimbDTO = reimbService.convertToDTO(result);
				System.out.println("Successful!");
				PrintWriter pw = response.getWriter();
				String json = om.writeValueAsString(reimbDTO);
				pw.println(json);
				System.out.println("JSON:\n" + json);
				response.setContentType("application/json");
				response.setStatus(200); // SUCCESSFUL!
			} else {
				System.out.println("Sorry, Azhya....i have failed you :(....");
				response.setContentType("application/json");
				response.setStatus(204); // this means that the connection was successful but insert failed!
			}

		log.info("Leaving request helper at processSubmitReimb()...");

	}

	public static void processViewPastReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("User choose to view their past reimbursements.");
		System.out.println("Let's get started...");
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		System.out.println(body);
		// use body to make a reimbTemplate
		//ReimbTemplate reimbAttempt = om.readValue(body, ReimbTemplate.class);

			// convert template into POJO
			Reimbursement r = reimbService.convertToReimb(reimbAttempt);
			// insert new reimb into database
			int id = reimbService.addReimbursement(r);
			System.out.println("back in request helper. converting data into json...");
			Reimbursement result = reimbService.getReimbursementById(id);
			
			//check that we got a new reimbursement in database
			if (result != null) {
				ReimbursementDTO reimbDTO = reimbService.convertToDTO(result);
				System.out.println("Successful!");
				PrintWriter pw = response.getWriter();
				String json = om.writeValueAsString(reimbDTO);
				pw.println(json);
				System.out.println("JSON:\n" + json);
				response.setContentType("application/json");
				response.setStatus(200); // SUCCESSFUL!
			} else {
				System.out.println("Sorry, Azhya....i have failed you :(....");
				response.setContentType("application/json");
				response.setStatus(204); // this means that the connection was successful but insert failed!
			}

		log.info("Leaving request helper at processSubmitReimb()...");
	}

}
