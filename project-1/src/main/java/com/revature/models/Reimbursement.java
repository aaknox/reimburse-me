package com.revature.models;

import java.time.LocalDateTime;

public class Reimbursement {
	// fields
	private int rId;
	private int amount;
	private LocalDateTime submissionDateTime;
	private LocalDateTime resolutionDateTime;
	private String receiptFilePath;
	private int authorId;
	private int resolverId;
	private ReimbursementStatus status;
	private ReimbursementType type;
	
	

	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int rId, int amount, LocalDateTime submissionDateTime, LocalDateTime resolutionDateTime,
			String receiptFilePath, int authorId, int resolverId, ReimbursementStatus status, ReimbursementType type) {
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

	public LocalDateTime getSubmissionDateTime() {
		return submissionDateTime;
	}

	public void setSubmissionDateTime(LocalDateTime submissionDateTime) {
		this.submissionDateTime = submissionDateTime;
	}

	public LocalDateTime getResolutionDateTime() {
		return resolutionDateTime;
	}

	public void setResolutionDateTime(LocalDateTime resolutionDateTime) {
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

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
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
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submissionDateTime == null) ? 0 : submissionDateTime.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submissionDateTime == null) {
			if (other.submissionDateTime != null)
				return false;
		} else if (!submissionDateTime.equals(other.submissionDateTime))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", amount=" + amount + ", submissionDateTime=" + submissionDateTime
				+ ", resolutionDateTime=" + resolutionDateTime + ", receiptFilePath=" + receiptFilePath + ", authorId="
				+ authorId + ", resolverId=" + resolverId + ", status=" + status + ", type=" + type + "]";
	}

}
