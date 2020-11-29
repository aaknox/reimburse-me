package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementService {
	public int addReimbursement(Reimbursement r);
	
	public Reimbursement getReimbursementById(int id);
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getReimbursementsByStatusId(int statusId);
	public List<Reimbursement> getReimbursementsByAuthorId(int authorId);
	public List<Reimbursement> getReimbursementsByAuthorId_NotPending(int authorId);
	public List<Reimbursement> getReimbursementsByAuthorId_Pending(int authorId);
	
	public void modifyReimbursement(Reimbursement r);
	
	public void deleteReimbursement(Reimbursement r);

}
