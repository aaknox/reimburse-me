package com.revature.util;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ReimbTemplate {
	private String amount;
	private String submissionDate = LocalDateTime.now().toString();
	private String resolutionDate;
	private String description;
	private String receipt;
	private String authorId;
	private String resolverId;
	private String[] reimbursementStatus = new String[2];
	private String[] reimbursementType = new String[2];

	public ReimbTemplate() {
		// TODO Auto-generated constructor stub
	}

	public ReimbTemplate(String amount, String submissionDate, String resolutionDate, String description,
			String receipt, String authorId, String resolverId, String[] reimbursementStatus,
			String[] reimbursementType) {
		super();
		this.amount = amount;
		this.submissionDate = submissionDate;
		this.resolutionDate = resolutionDate;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.reimbursementStatus = reimbursementStatus;
		this.reimbursementType = reimbursementType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getResolverId() {
		return resolverId;
	}

	public void setResolverId(String resolverId) {
		this.resolverId = resolverId;
	}

	public String[] getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(String[] reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	public String[] getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String[] reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	@Override
	public String toString() {
		return "ReimbTemplate [amount=" + amount + ", submissionDate=" + submissionDate + ", resolutionDate="
				+ resolutionDate + ", description=" + description + ", receipt=" + receipt + ", authorId=" + authorId
				+ ", resolverId=" + resolverId + ", reimbursementStatus=" + Arrays.toString(reimbursementStatus)
				+ ", reimbursementType=" + Arrays.toString(reimbursementType) + "]";
	}

}
