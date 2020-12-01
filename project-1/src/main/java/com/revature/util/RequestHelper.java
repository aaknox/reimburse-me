package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	public static void processViewAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("Admin choose to gather all employees.");
		System.out.println("Let's get started...");
		
		//This is a GET request, meaning no request body...
		List<User> list = userService.getAllEmployees();
		List<UserDTO> listDTO = new ArrayList<>();
		
		//check that we got a new reimbursement in database
		if (list != null) {
			for (User u : list) {
				System.out.println(u);
				listDTO.add(userService.convertToDTO(u));
			}

			String json = om.writeValueAsString(listDTO);
			PrintWriter pw = response.getWriter();
			pw.println(json);
			System.out.println("JSON:\n" + json);
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
		}else {
			System.out.println("Sorry, Azhya....i have failed you :(....");
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but select statement failed!
		}
		log.info("Leaving request helper at processViewAllEmployees()...");

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
		
		//This is a GET request, meaning no request body...
		String paramStr = request.getQueryString();
		
		System.out.println("Param string: " + paramStr);
		String idStr = paramStr.substring(3);
		int authorId = Integer.parseInt(idStr);
		System.out.println("TEST- My Author ID is: " + authorId);
		List<Reimbursement> list = reimbService.getReimbursementsByAuthorId_NotPending(authorId);
		List<ReimbursementDTO> listDTO = new ArrayList<>();
		
		//check that we got a new reimbursement in database
		if (list != null) {
			for (Reimbursement r : list) {
				listDTO.add(reimbService.convertToDTOFull(r));
			}

			String json = om.writeValueAsString(listDTO);
			PrintWriter pw = response.getWriter();
			pw.println(json);
			System.out.println("JSON:\n" + json);
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
		}else {
			System.out.println("Sorry, Azhya....i have failed you :(....");
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but select statement failed!
		}
		log.info("Leaving request helper at processViewPastReimb()...");
	}

	public static void processViewPendingReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("User choose to view their pending reimbursements.");
		System.out.println("Let's get started...");
		
		//This is a GET request, meaning no request body...
		String paramStr = request.getQueryString();
		
		System.out.println("Param string: " + paramStr);
		String idStr = paramStr.substring(3);
		int authorId = Integer.parseInt(idStr);
		System.out.println("TEST- My Author ID is: " + authorId);
		List<Reimbursement> list = reimbService.getReimbursementsByAuthorId_Pending(authorId);
		List<ReimbursementDTO> listDTO = new ArrayList<>();
		
		//check that we got a new reimbursement in database
		if (list != null) {
			for (Reimbursement r : list) {
				listDTO.add(reimbService.convertToDTO(r));
			}

			String json = om.writeValueAsString(listDTO);
			PrintWriter pw = response.getWriter();
			pw.println(json);
			System.out.println("JSON:\n" + json);
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
		}else {
			System.out.println("Sorry, Azhya....i have failed you :(....");
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but select statement failed!
		}
		log.info("Leaving request helper at processViewPendingReimb()...");
	}

	public static void processUpdateProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("User choose to update their profile information.");
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
		UserDTO updateAttempt = om.readValue(body, UserDTO.class);

		// convert template into POJO
		User u = userService.convertToUser(updateAttempt);
		// update user info in database
		userService.modifyUser(u);
		System.out.println("back in request helper. converting data into json...");
		User result = userService.getUserByUserId(u.getUserId());
		//check that we got a new reimbursement in database
		if (result != null) {
			UserDTO uDTO = userService.convertToDTO(result);
			System.out.println("Successful!");
			PrintWriter pw = response.getWriter();
			String json = om.writeValueAsString(uDTO);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
		} else {
			System.out.println("Sorry, Azhya....i have failed you :(....");
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but update failed!
		}

		log.info("Leaving request helper at processUpdateProfile()...");
		
	}

	public static void processViewAllReimb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("Admin choose to view all reimbursements.");
		System.out.println("Let's get started...");
		
		//This is a GET request, meaning no request body...
		List<Reimbursement> list = reimbService.getAllReimbursements();
		List<ReimbursementDTO> listDTO = new ArrayList<>();
		
		//check that we got a new reimbursement in database
		if (list != null) {
			for (Reimbursement r : list) {
				System.out.println(r);
				//check if resolutiondate exist in r
				if(r.getResolutionDateTime() == null) {
					System.out.println("empty settings applied");
					//run this reimbDTO with empty settings
					listDTO.add(reimbService.convertToDTO(r));
				}else {
					System.out.println("full settings applied");
					//run the conversion with full settings
					listDTO.add(reimbService.convertToDTOFull(r));
				}
			}

			String json = om.writeValueAsString(listDTO);
			PrintWriter pw = response.getWriter();
			pw.println(json);
			System.out.println("JSON:\n" + json);
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
		}else {
			System.out.println("Sorry, Azhya....i have failed you :(....");
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but select statement failed!
		}
		log.info("Leaving request helper at processViewAllReimb()...");
		
	}

	public static void processViewAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("Admin choose to view all users.");
		System.out.println("Let's get started...");
		
		//This is a GET request, meaning no request body...
		List<User> list = userService.getAllUsers();
		List<UserDTO> listDTO = new ArrayList<>();
		
		//check that we got a new reimbursement in database
		if (list != null) {
			for (User u : list) {
				System.out.println(u);
				listDTO.add(userService.convertToDTO(u));
			}

			String json = om.writeValueAsString(listDTO);
			PrintWriter pw = response.getWriter();
			pw.println(json);
			System.out.println("JSON:\n" + json);
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
		}else {
			System.out.println("Sorry, Azhya....i have failed you :(....");
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but select statement failed!
		}
		log.info("Leaving request helper at processViewAllUsers()...");
		
	}

	public static void processAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processAddUser...");
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

		
		RegisterTemplate registerAttempt = om.readValue(body, RegisterTemplate.class);
		String creationDate = LocalDate.now().toString();
		registerAttempt.setHireDate(creationDate);
		log.info("User attempted to register with information:\n " + registerAttempt);
		//convert into DTO
		UserDTO dto = new UserDTO(
				registerAttempt.getUsername(),
				registerAttempt.getPassword(),
				registerAttempt.getFirstName(),
				registerAttempt.getLastName(),
				registerAttempt.getEmail(),
				registerAttempt.getHireDate(),
				registerAttempt.getUserRoleId(),
				registerAttempt.getUserRoleName()
				);
		System.out.println(dto);
		//convert into POJO
		User e = userService.convertToUser(dto);
		System.out.println(e);
		userService.addUser(e);

		if (e != null) {
			PrintWriter pw = response.getWriter();

			// create an EmployeeDTO from Employee object
			// create a method to create an employee DTO (in EmployeeService class)
			UserDTO eDTO = userService.convertToDTO(e);
			System.out.println(eDTO);
			String json = om.writeValueAsString(eDTO);
			pw.println(json);
			System.out.println("JSON:\n" + json);
			
			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("User has successfully been created.");
		} else {
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but no user created!
		}
		log.info("leaving request helper now...");
		
	}

	public static void processDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("inside of request helper...processDeleteUser...");
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

		
		DeleteTemplate deleteAttempt = om.readValue(body, DeleteTemplate.class);
		
		log.info("User attempted to delete with information:\n " + deleteAttempt);
		//get the real user by id
		User u = userService.getUserByUserId(deleteAttempt.getUserId());
		System.out.println(u);
		if (u != null) {
		//delete user
		userService.deleteUser(u);
		//write response
		PrintWriter pw = response.getWriter();

		// create an EmployeeDTO from Employee object
		// create a method to create an employee DTO (in EmployeeService class)
		UserDTO eDTO = userService.convertToDTO(u);
		System.out.println(eDTO);
		String json = om.writeValueAsString(eDTO);
		pw.println(json);
		System.out.println("JSON:\n" + json);
		
		response.setContentType("application/json");
		response.setStatus(200); // SUCCESSFUL!
		log.info("User has successfully been removed.");
	} else {
		System.out.println("ABORT, ABORT!!!!!!");
		response.setContentType("application/json");
		response.setStatus(204); // this means that the connection was successful but no user deletion!
	}
	log.info("leaving request helper now...");
		
	}

}
