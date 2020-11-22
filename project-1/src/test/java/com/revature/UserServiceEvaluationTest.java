package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserDaoImpl;
import com.revature.services.UserServiceImpl;

public class UserServiceEvaluationTest {

	@InjectMocks
	private UserServiceImpl service;
	@Mock
	private UserDaoImpl daoMock;

	@Before
	public void setUp() throws Exception {
		// TODO: Make real unit tests using Mockito to mock DAOs for Service layer
		// MAKE SURE YOU START TOMCAT BEFORE RUNNING JUNIT TEST
		BasicConfigurator.configure();
		service = new UserServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

//	@Test //test passed
//	public void testInsert() {
//		//test object declarations
//		User testUser = new User("testMe", "password", "Babe", "Ruth", "mrhomerun@gmail.com", LocalDate.now(), new UserRole(1, "EMPLOYEE"));
//		//run test on method using service injector
//		service.addUser(testUser);
//		//verify that the dao mock ran with injector
//		verify(daoMock, times(0)).insertUser(testUser);
//	}

//	@Test //test passed
//	public void testSelectByUsername() {
//		System.out.println("start of test 1");
//		// test object declarations
//		String testUsername = "aaknox";
//		// run test on method using service injector
//		User test = new User(3, "aaknox", "password", "Azhya", "Knox", "azhya.knox@gmail.com",
//				LocalDate.parse("2018-12-18"), new UserRole(2, "FINANCE MANAGER"));
//		// verify
//		assertEquals(test, service.getUserByUsername(testUsername));
//		System.out.println("end of test 1");
//	}
//
//	@Test //test passed
//	public void testNotAUser_SelectByUsername() {
//		System.out.println("start of test 2");
//		// test object declarations
//		String notUsername = "asjdhkajfh";
//		// run test on method using service injector
//		User test = new User(3, "aaknox", "password", "Azhya", "Knox", "azhya.knox@gmail.com",
//				LocalDate.parse("2018-12-18"), new UserRole(2, "FINANCE MANAGER"));
//		// verify
//		assertNotEquals(test, service.getUserByUsername(notUsername));
//		System.out.println("end of test 2");
//	}

//	@Test //test passed
//	public void testUpdate() {
//		System.out.println("start of test 3");
//		// test object declarations
//		User test = new User(3, "aaknox", "password", "Azhya", "Knox", "azhya.knox@gmail.com",
//				LocalDate.parse("2020-01-18"), new UserRole(2, "FINANCE MANAGER"));
//		// run test on method using service injector
//		service.modifyUser(test);
//		// verify
//		System.out.println(service.getUserByUsername(test.getUsername()));
//	}
//	@Test //test passed
//	public void testDelete() {
//		System.out.println("start of test 3");
//		// test object declarations
//		User test = service.getUserByUsername("testMe");
//		System.out.println(service.getUserByUsername(test.getUsername()));
//
//		// run test on method using service injector
//		service.deleteUser(test);
//	}

//	@Test //test passed
//	public void testSelectAllUsers() {
//		// verify
//		List<User> list = service.getAllUsers();
//		System.out.println("-----------User List---------");
//		for (User user : list) {
//			System.out.println(user);
//		}
//		System.out.println("------------------------------");
//	}
	
//	@Test //test passed
//	public void testUpdatePassword() {
//		String myNewPassword = "testpassword";
//		List<User> list = service.getAllUsers();
//		System.out.println("-----------BEFORE UPDATE---------");
//		for (User user : list) {
//			System.out.println(user);
//		}
//		System.out.println("------------------------------");
//		
//		service.modifyPassword("aaknox", myNewPassword);
//		
//		List<User> list2 = service.getAllUsers();
//		System.out.println("-----------AFTER UPDATE---------");
//		for (User user : list2) {
//			System.out.println(user);
//		}
//		System.out.println("------------------------------");
//		
//	}
	
}
