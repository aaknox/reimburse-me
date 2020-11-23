package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	// CRUD NETHODS ONLY
		// create
		public void insertReimbursement(Reimbursement reimb);

		// read
		public List<Reimbursement> selectAllReimbursements();

		public Reimbursement selectReimbursementById(int id);
		
		public List<Reimbursement> selectReimbursementsByStatusId(int sId);
		
		
		// update
		public void updateReimbursement(Reimbursement reimb);

		// delete
		public void deleteReimbursement(Reimbursement reimb);
}
