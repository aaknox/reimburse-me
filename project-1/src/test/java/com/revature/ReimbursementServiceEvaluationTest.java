package com.revature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.repositories.ReimbursementDaoImpl;
import com.revature.services.ReimbursementServiceImpl;
import com.revature.util.HibernateUtil;

public class ReimbursementServiceEvaluationTest {
	@InjectMocks
	HibernateUtil util;
	
	@InjectMocks
	ReimbursementServiceImpl service;
	
	@Mock
	ReimbursementDaoImpl daoMock;

	@Before
	public void setUp() throws Exception {
		this.sessionMock = mock(MySession.class);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
