package com.revature;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDaoImpl;
import com.revature.repositories.UserDaoImpl;
import com.revature.services.ReimbursementServiceImpl;
import com.revature.services.UserServiceImpl;

public class ReimbursementServiceEvaluationTest {
	
	@InjectMocks
	ReimbursementServiceImpl service;
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserDaoImpl userDaoMock;
	
	@Mock
	ReimbursementDaoImpl daoMock;
	
	@Before
	public void setUp() throws Exception {
		// TODO: Make real unit tests using Mockito to mock DAOs for Service layer
		// MAKE SURE YOU START TOMCAT BEFORE RUNNING JUNIT TEST
		BasicConfigurator.configure();
		service = new ReimbursementServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

//	@Test //test passed
//	public void testAddReimbursement() {
//		User author = userService.getUserByUsername("employee001");
//		Reimbursement test = new Reimbursement(
//					BigDecimal.valueOf(123.45),
//					LocalDateTime.now(),
//					null,
//					"another office party",
//					null,
//					author,
//					null,
//					new ReimbursementStatus(2, "PENDING"),
//					new ReimbursementType(4, "OTHER")
//				);
//		Reimbursement test2 = new Reimbursement(
//				BigDecimal.valueOf(75.50),
//				LocalDateTime.now(),
//				null,
//				"travel expenses to state convention",
//				null,
//				author,
//				null,
//				new ReimbursementStatus(2, "PENDING"),
//				new ReimbursementType(2, "TRAVEL")
//			);
//		List<Reimbursement> list = service.getAllReimbursements();
//		System.out.println("---------------BEFORE ADDITION-------------");
//		for (Reimbursement reimbursement : list) {
//			System.out.println(reimbursement);
//		}
//		System.out.println("-------------------------------------------");
//		
//		//run test
//		service.addReimbursement(test);
//		service.addReimbursement(test2);
//		
//		//verify
//		List<Reimbursement> list2 = service.getAllReimbursements();
//		System.out.println("---------------AFTER ADDITION-------------");
//		for (Reimbursement reimbursement : list2) {
//			System.out.println(reimbursement);
//		}
//		System.out.println("-------------------------------------------");
//	}
	
//	@Test //test passed
//	public void testGetReimbursementsByStatus() {
//		//verify
//		//1 = approved
//		//2 = pending
//		//3 = denied
//		List<Reimbursement> list2 = service.getReimbursementsByStatusId(2);
//		System.out.println("---------------STATUS = PENDING-------------");
//		for (Reimbursement reimbursement : list2) {
//			System.out.println(reimbursement);
//		}
//		System.out.println("-------------------------------------------");
//	}

//	@Test //test passed
//	public void testUpdateReimbursement() {
//		//test object declarations
//		Reimbursement test = service.getReimbursementById(400);
//		test.setStatus(new ReimbursementStatus(3, "DENIED"));
//		System.out.println("Before test update: " + test);
//		service.modifyReimbursement(test);
//		Reimbursement result = service.getReimbursementById(400);
//		System.out.println("After test update: " + result);
//	}
	
//	@Test //test passed
//	public void testDeleteReimbursement() {
//		List<Reimbursement> list = service.getAllReimbursements();
//		System.out.println("---------------BEFORE DELETION-------------");
//		for (Reimbursement reimbursement : list) {
//			System.out.println(reimbursement);
//		}
//		System.out.println("-------------------------------------------");
//		Reimbursement test = service.getReimbursementById(500);
//		service.deleteReimbursement(test);
//		List<Reimbursement> list2 = service.getAllReimbursements();
//		System.out.println("---------------AFTER DELETION-------------");
//		for (Reimbursement reimbursement : list2) {
//			System.out.println(reimbursement);
//		}
//		System.out.println("-------------------------------------------");
//	}
}
