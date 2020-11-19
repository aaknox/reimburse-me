package com.revature.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursements")
public class ReimbursementBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// fields
	
	@Id
	@Column(name = "reimb_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reimb_sequence")
	@SequenceGenerator(name="reimb_sequence", sequenceName = "reimb_sequence", initialValue = 100, allocationSize = 100)
	private int rId;
	
	@Column(name="reimb_amount", precision = 10, scale = 2)
	private int amount;
	
	@Column(name="reimb_submitted")
	private String submissionDateTime;
	
	@Column(name="reimb_resolved")
	private String resolutionDateTime;
	
	@Column(name="reimb_description")
	private String rDescription;
	
	@Column(name="reimb_receipt")
	private String receiptFilePath;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UserBean.class)
	@JoinColumn(name = "fk_constraint_authorToErsUsers", referencedColumnName = "reimb_author_id")
	private int authorId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UserBean.class)
	@JoinColumn(name = "fk_constraint_resolverToErsUsers", referencedColumnName = "reimb_resolver_id")
	private int resolverId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ReimbursementStatusBean.class)
	@JoinColumn(name = "fk_constraint_ersReimbToReimbStatus", referencedColumnName = "reimb_status_id")
	private int status;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ReimbursementTypeBean.class)
	@JoinColumn(name = "fk_constraint_ersReimbToReimbType", referencedColumnName = "reimb_type_id")
	private int type;

	// no args
	public ReimbursementBean() {
		// TODO Auto-generated constructor stub
	}

	// all args
	public ReimbursementBean(int rId, int amount, String submissionDateTime, String resolutionDateTime,
			String receiptFilePath, int authorId, int resolverId, int status, int type) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.submissionDateTime = submissionDateTime;
		this.resolutionDateTime = resolutionDateTime;
		this.receiptFilePath = receiptFilePath;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.status = status;
		this.type = type;
	}

	// without rid
	public ReimbursementBean(int amount, String submissionDateTime, String resolutionDateTime, String receiptFilePath,
			int authorId, int resolverId, int status, int type) {
		super();
		this.amount = amount;
		this.submissionDateTime = submissionDateTime;
		this.resolutionDateTime = resolutionDateTime;
		this.receiptFilePath = receiptFilePath;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.status = status;
		this.type = type;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSubmissionDateTime() {
		return submissionDateTime;
	}

	public void setSubmissionDateTime(String submissionDateTime) {
		this.submissionDateTime = submissionDateTime;
	}

	public String getResolutionDateTime() {
		return resolutionDateTime;
	}

	public void setResolutionDateTime(String resolutionDateTime) {
		this.resolutionDateTime = resolutionDateTime;
	}

	public String getReceiptFilePath() {
		return receiptFilePath;
	}

	public void setReceiptFilePath(String receiptFilePath) {
		this.receiptFilePath = receiptFilePath;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + authorId;
		result = prime * result + rId;
		result = prime * result + ((receiptFilePath == null) ? 0 : receiptFilePath.hashCode());
		result = prime * result + ((resolutionDateTime == null) ? 0 : resolutionDateTime.hashCode());
		result = prime * result + resolverId;
		result = prime * result + status;
		result = prime * result + ((submissionDateTime == null) ? 0 : submissionDateTime.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementBean other = (ReimbursementBean) obj;
		if (amount != other.amount)
			return false;
		if (authorId != other.authorId)
			return false;
		if (rId != other.rId)
			return false;
		if (receiptFilePath == null) {
			if (other.receiptFilePath != null)
				return false;
		} else if (!receiptFilePath.equals(other.receiptFilePath))
			return false;
		if (resolutionDateTime == null) {
			if (other.resolutionDateTime != null)
				return false;
		} else if (!resolutionDateTime.equals(other.resolutionDateTime))
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (status != other.status)
			return false;
		if (submissionDateTime == null) {
			if (other.submissionDateTime != null)
				return false;
		} else if (!submissionDateTime.equals(other.submissionDateTime))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementBean [rId=" + rId + ", amount=" + amount + ", submissionDateTime=" + submissionDateTime
				+ ", resolutionDateTime=" + resolutionDateTime + ", receiptFilePath=" + receiptFilePath + ", authorId="
				+ authorId + ", resolverId=" + resolverId + ", status=" + status + ", type=" + type + "]";
	}

}
