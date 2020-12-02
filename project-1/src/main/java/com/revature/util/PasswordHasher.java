package com.revature.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
	private static int workload = 12;
	
	public static String hashPassword(String password_plaintext) {
		// Hash a password for the first time
		String hashed = BCrypt.hashpw(password_plaintext, BCrypt.gensalt(workload));
		return hashed;
	}

	public static boolean checkPassword(String candidate, String hashed) {
		// Check that an unencrypted password matches one that has
		// previously been hashed
		if (BCrypt.checkpw(candidate, hashed)) {
			System.out.println("It matches");
			return true;
		}
		else {
			System.out.println("It does not match");
			return false;
		}
		
	}
	
}
