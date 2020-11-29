package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	// CRUD NETHODS ONLY
		// create
		public int insertReimbursement(Reimbursement reimb);

		// read
		public List<Reimbursement> selectAllReimbursements();

		public Reimbursement selectReimbursementById(int id);
		
		public List<Reimbursement> selectReimbursementsByStatusId(int sId);
		
		public List<Reimbursement> selectReimbursementsByAuthorId(int authorId);
		
		public List<Reimbursement> selectReimbursementsByAuthorId_NotPending(int authorId);
		
		public List<Reimbursement> selectReimbursementsByAuthorId_Pending(int authorId);
		
		// update
		public void updateReimbursement(Reimbursement reimb);

		// delete
		public void deleteReimbursement(Reimbursement reimb);
}
