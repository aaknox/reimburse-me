package com.revature.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
	private static int workload = 6;
	
	public static String hashPassword(String password_plaintext) {
		// Hash a password for the first time
		String hashed = BCrypt.hashpw(password_plaintext, BCrypt.gensalt(workload));
		return hashed;
	}
	
	public static void storeHash(int id, String password) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ers_verification VALUES (?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, password);
			ps.setString(3, hashPassword(password));
			ps.executeUpdate();
			System.out.println("Save of verfiication data complete!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getHash (int id, String password_plaintext) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT ers_ver_password, ers_ver_hash from ers_verification WHERE ers_ver_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String hash = rs.getString(2);
				return hash;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void removeHash(int id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM ers_verification WHERE ers_ver_id =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Deletion data complete!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateHash(int id, String password) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE ers_verification SET ers_ver_password = ?, ers_ver_hash = ? WHERE ers_ver_id =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, hashPassword(password));
			ps.setInt(3, id);
			ps.executeUpdate();
			System.out.println("Update data complete!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
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
