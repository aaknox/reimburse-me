package com.revature.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

public class PasswordHasher {
	private static int workload = 12;
	
	public static String hashPassword(String password_plaintext) {
		String hashed_password = BCrypt.withDefaults().hashToString(workload, password_plaintext.toCharArray());

		return(hashed_password);
	}

	public static Result checkPassword(String password_plaintext, String stored_hash) {
		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		BCrypt.Result result = BCrypt.verifyer().verify(password_plaintext.toCharArray(), stored_hash);
		return(result);
	}
	
	public static void main(String[] args) {
		String stored_db_passwd = "abcdefghijklmnopqrstuvwxyz";
		String stored_db_hash = hashPassword(stored_db_passwd);
		
		System.out.println("Testing BCrypt Password hashing and verification");
		System.out.println("Test password: " + stored_db_passwd);
		System.out.println("Test stored hash: " + stored_db_hash);
		System.out.println("TESTING PASSWORD HASHER...");
		System.out.println();

		String given_passwd = "abcdefghijklmnopqrstuvwxyz";
		String given_hash = hashPassword(given_passwd);
		System.out.println("Given Password: " + given_passwd);
		System.out.println("Given hash: " + given_hash);
		System.out.println();
		System.out.println("Verifying that hashes from stored & given values match...");
		System.out.println();

		if(checkPassword(stored_db_passwd, stored_db_hash) != null && 
				checkPassword(given_passwd, given_hash) != null 
				&& stored_db_hash.equals(given_hash)) {
			System.out.println("Passwords Match!");
		}else {
			System.out.println("Passwords Don't Match!");
		}

	}
}
