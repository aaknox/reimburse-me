package com.revature.repositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static Logger log = Logger.getLogger(ReimbursementDaoImpl.class);
	private static Session session = HibernateUtil.getSession();

	@Override
	public void insertReimbursement(Reimbursement reimb) {

		Transaction tx = session.beginTransaction();

		try {
			session.save(reimb);
		} catch (Exception e) {
			log.warn("Failed to insert reimb into database. Stack Trace: ", e);
		}

		tx.commit();

		session.close();

	}

	@Override
	public List<Reimbursement> selectAllReimbursements() {
		List<Reimbursement> reimbList = session.createQuery("from Reimbursement", Reimbursement.class).list();
		session.close();
		return reimbList;
	}

	@Override
	public Reimbursement selectReimbursementById(int id) {
		Reimbursement reimb = session.get(Reimbursement.class, id);
		session.close();
		return reimb;
	}

	@Override
	public List<Reimbursement> selectReimbursementsByStatusId(int sId) {
		List<Reimbursement> reimbList = session
				.createQuery("from Reimbursement where status.getStatusId()='" + sId + "'", Reimbursement.class).list();
		session.close();
		return reimbList;
	}

	@Override
	public List<Reimbursement> selectReimbursementsByStatusName(String statusName) {
		List<Reimbursement> reimbList = session.createQuery(
				"from Reimbursement where status.getStatusName()='" + statusName + "'", Reimbursement.class).list();
		session.close();
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

		session.close();
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

		session.close();

	}

}