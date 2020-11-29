package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static Logger log = Logger.getLogger(ReimbursementDaoImpl.class);
	private static Session session = HibernateUtil.getSession();

	@Override
	public int insertReimbursement(Reimbursement reimb) {
		System.out.println("inside DAO Layer...inserting reimb...");;
		int reimbId = 0;

		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ers_reimbursements(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author_id, reimb_resolver_id, reimb_status_id, reimb_type_id)" + 
					"VALUES (?, ?, NULL, ?, ?, ?, NULL, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setBigDecimal(1, reimb.getAmount());
			ps.setTimestamp(2, Timestamp.valueOf(reimb.getSubmissionDateTime()));
			ps.setString(3, reimb.getDescription());
			ps.setBytes(4, reimb.getReceipt());
			ps.setInt(5, reimb.getAuthor().getUserId());
			ps.setInt(6, reimb.getStatus().getStatusId());
			ps.setInt(7, reimb.getType().getTypeId());
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating new reimbursement failed, no rows affected.");
			}
			try (ResultSet genKeys = ps.getGeneratedKeys()){
				if(genKeys.next()) {
					reimbId = genKeys.getInt("reimb_id");
					System.out.println("The new reimbursement ID is: " + reimbId);
				}
			} catch (Exception e) {
				throw new SQLException("Creating new reimbursement failed, no ID obtained.");
			}
			;
			System.out.println("Save complete.");
		} catch (Exception e) {
			log.warn("Failed to insert reimb into database. Stack Trace: ", e);
		}

		return reimbId;
	}

	@Override
	public List<Reimbursement> selectAllReimbursements() {
		List<Reimbursement> reimbList = session.createQuery("from Reimbursement", Reimbursement.class).list();
		return reimbList;
	}

	@Override
	public Reimbursement selectReimbursementById(int id) {
		System.out.println("inside of Reimb DAO Layer...selectng by reimbId: " + id);
		Reimbursement reimb = session.get(Reimbursement.class, id);
		System.out.println("Reimbursement found: " + reimb);
		return reimb;
	}

	@Override
	public List<Reimbursement> selectReimbursementsByStatusId(int statusId) {
		List<Reimbursement> reimbList = session.createNativeQuery(
				"Select * from ers_reimbursements where reimb_status_id ='" + statusId + "'", Reimbursement.class).list();
		return reimbList;
	}
	
	@Override
	public List<Reimbursement> selectReimbursementsByAuthorId(int authorId) {
		List<Reimbursement> reimbList = session.createNativeQuery(
				"Select * from ers_reimbursements where reimb_author_id ='" + authorId + "'", Reimbursement.class).list();
		return reimbList;
	}
	
	//AND reimb_status_id != 2 <-- not pending
	@Override
	public List<Reimbursement> selectReimbursementsByAuthorId_NotPending(int authorId) {
		List<Reimbursement> reimbList = session.createNativeQuery(
				"Select * from ers_reimbursements where reimb_author_id ='" + authorId + "' AND reimb_status_id != 2", Reimbursement.class).list();
		return reimbList;
	}
	
	@Override
	public List<Reimbursement> selectReimbursementsByAuthorId_Pending(int authorId) {
		List<Reimbursement> reimbList = session.createNativeQuery(
				"Select * from ers_reimbursements where reimb_author_id ='" + authorId + "' AND reimb_status_id = 2", Reimbursement.class).list();
		return reimbList;
	}
	
	@Override
	public void updateReimbursement(Reimbursement reimb) {
		Transaction tx = session.beginTransaction();

		try {
			session.update(reimb);
		} catch (Exception e) {
			log.warn("Failed to update reimb into database. Stack Trace: ", e);
		}

		tx.commit();
	}

	@Override
	public void deleteReimbursement(Reimbursement reimb) {
		Transaction tx = session.beginTransaction();

		try {
			session.delete(reimb);
		} catch (Exception e) {
			log.warn("Failed to delete reimb into database. Stack Trace: ", e);
		}

		tx.commit();

	}

}
