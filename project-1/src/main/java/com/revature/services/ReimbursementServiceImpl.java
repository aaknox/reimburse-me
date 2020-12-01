package com.revature.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoImpl;
import com.revature.util.ReimbTemplate;

public class ReimbursementServiceImpl implements ReimbursementService {
	private static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	private static ReimbursementDao reimbDao = new ReimbursementDaoImpl();
	private static UserServiceImpl userServiceImpl = new UserServiceImpl();
	
	@Override
	public int addReimbursement(Reimbursement r) {
		log.info("Inside ReimbursementServiceImpl - adding reimbursement to database...");
		int id = 0;
		try {
			log.info("Starting to process request of submission...");
			id = reimbDao.insertReimbursement(r);
			System.out.println("reimbursement added. leaving service layer...");
			return id;
		} catch (Exception e) {
			log.warn("Addition failed here. Stack Trace: ", e);
		}
		return 0;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		log.info("Inside ReimbursementServiceImpl - gathering all reimbursement records");
		List<Reimbursement> list = reimbDao.selectAllReimbursements();
		if (list != null) {
			log.info("Got the list!");
			return list;
		} else {
			log.warn("No records could be found.");
			return null;
		}
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		log.info("Inside ReimbursementServiceImpl - gathering reimbursement record with reimb id number: " + id);
		Reimbursement reimb = reimbDao.selectReimbursementById(id);
		System.out.println("Back in service layer, get by reimb id. Reimbursement found: " + reimb);
		return reimb;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatusId(int statusId) {
		log.info(
				"Inside ReimbursementServiceImpl - gathering reimbursement records with status id number: " + statusId);
		List<Reimbursement> list = reimbDao.selectReimbursementsByStatusId(statusId);
		log.info("Got the reimbursement list marked with status id: " + statusId);
		return list;
	}
	
	@Override
	public List<Reimbursement> getReimbursementsByAuthorId(int authorId) {
		log.info(
				"Inside ReimbursementServiceImpl - gathering reimbursement records with author id number: " + authorId);
		List<Reimbursement> list = reimbDao.selectReimbursementsByAuthorId(authorId);
		log.info("Got the reimbursement list marked with author id: " + authorId);
		return list;
	}
	
	@Override
	public List<Reimbursement> getReimbursementsByAuthorId_NotPending(int authorId) {
		log.info(
				"Inside ReimbursementServiceImpl - gathering reimbursement records with author id number: " + authorId);
		log.info("Excluding any PENDING reimbursements...");
		List<Reimbursement> list = reimbDao.selectReimbursementsByAuthorId_NotPending(authorId);
		log.info("Got the reimbursement list marked with author id: " + authorId);
		return list;
	}
	
	@Override
	public List<Reimbursement> getReimbursementsByAuthorId_Pending(int authorId) {
		log.info(
				"Inside ReimbursementServiceImpl - gathering reimbursement records with author id number: " + authorId);
		log.info("PENDING reimbursements ONLY...");
		List<Reimbursement> list = reimbDao.selectReimbursementsByAuthorId_Pending(authorId);
		log.info("Got the reimbursement list marked with author id: " + authorId);
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
	
	public Reimbursement convertToReimb(ReimbTemplate temp) {
		double rAmount = Double.parseDouble(temp.getAmount());
		LocalDateTime sDateTime = LocalDateTime.now();
		LocalDateTime rDateTime = null;
		int statusId = Integer.parseInt(Arrays.asList(temp.getReimbursementStatus()).get(0));
		String statusName = Arrays.asList(temp.getReimbursementStatus()).get(1);
		int typeId = Integer.parseInt(Arrays.asList(temp.getReimbursementType()).get(0));
		String typeName = Arrays.asList(temp.getReimbursementType()).get(1);
		User author = userServiceImpl.getUserByUserId(Integer.valueOf(temp.getAuthorId()));
		User resolver = new User();
		
		return new Reimbursement(
					BigDecimal.valueOf(rAmount),
					sDateTime, rDateTime,
					temp.getDescription(),
					temp.getReceipt().getBytes(),
					author,
					resolver,
					new ReimbursementStatus(statusId, statusName),
					new ReimbursementType(typeId, typeName)
				);
	}
	
	public ReimbursementDTO convertToDTO(Reimbursement r) {
		System.out.println(r);
		return new ReimbursementDTO(
					r.getrId(),
					r.getAmount().toPlainString(),
					r.getSubmissionDateTime().toString(),
					"",
					r.getDescription(),
					r.getReceipt().toString(),
					r.getAuthor().getUserId(),
					0,
					r.getStatus().getStatusId(),
					r.getStatus().getStatusName(),
					r.getType().getTypeId(),
					r.getType().getTypeName()
				);
	}

	public ReimbursementDTO convertToDTOFull(Reimbursement r){
		//check for any empty resolverIds and resolutionDates
		System.out.println(r);
		return new ReimbursementDTO(
				r.getrId(),
				r.getAmount().toPlainString(),
				r.getSubmissionDateTime().toString(),
				r.getResolutionDateTime().toString(),
				r.getDescription(),
				r.getReceipt().toString(),
				r.getAuthor().getUserId(),
				r.getResolver().getUserId(),
				r.getStatus().getStatusId(),
				r.getStatus().getStatusName(),
				r.getType().getTypeId(),
				r.getType().getTypeName()
		);
	}
}
