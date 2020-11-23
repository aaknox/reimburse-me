package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoImpl;

public class ReimbursementServiceImpl implements ReimbursementService {
	private static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	private static ReimbursementDao reimbDao = new ReimbursementDaoImpl();
	
	@Override
	public void addReimbursement(Reimbursement r) {
		log.info("Inside ReimbursementServiceImpl - adding reimbursement to database...");
		try {
			reimbDao.insertReimbursement(r);
			log.info("Addition was successful!");
		} catch (Exception e) {
			log.warn("Addition failed here. Stack Trace: ", e);
		}
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		log.info("Inside ReimbursementServiceImpl - gathering all reimbursement records");
		List<Reimbursement> list = reimbDao.selectAllReimbursements();
		if(list != null) {
			log.info("Got the list!");
			return list;
		}else {
			log.warn("No records could be found.");
			return null;
		}
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String statusName) {
		log.info("Inside ReimbursementServiceImpl - gathering reimbursement records with status: " + statusName);
		List<Reimbursement> list = reimbDao.selectReimbursementsByStatusName(statusName);
		log.info("Got the reimbursement list marked with status: " + statusName);
		return list;
	}

	@Override
	public void modifyReimbursement(Reimbursement r) {
		log.info("Inside ReimbursementServiceImpl - updating given reimbursement info...");
		try {
			reimbDao.insertReimbursement(r);
			log.info("Update was successful!");
		} catch (Exception e) {
			log.warn("Update failed here. Stack Trace: ", e);
		}
	}

	@Override
	public void deleteReimbursement(Reimbursement r) {
		log.info("Inside ReimbursementServiceImpl - removing reimbursement from database...");
		try {
			reimbDao.deleteReimbursement(r);
			log.info("Deletion was successful!");
		} catch (Exception e) {
			log.warn("Deletion failed here. Stack Trace: ", e);
		}
	}

}
