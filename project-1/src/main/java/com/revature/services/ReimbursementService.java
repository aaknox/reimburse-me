package com.revature.services;

import java.util.List;

import com.revature.beans.ReimbursementBean;
import com.revature.models.Reimbursement;

public interface ReimbursementService {
	public void addReimbursement(Reimbursement r);
	
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getReimbursementsByStatus(String statusName);
	
	public void modifyReimbursement(Reimbursement r);
	
	public void deleteReimbursement(Reimbursement r);
	
	public ReimbursementBean convertToReimbursementBean(Reimbursement r);
	public Reimbursement convertToReimbursement(ReimbursementBean b);

}
