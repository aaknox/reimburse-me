package com.revature.models;

import java.io.Serializable;

public class ReimbursementDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rId;
	private String amount;
	private String submissionDate;
	private String resolutionDate;
	private String description;
	private String receipt;
	private int authorId;
	private int resolverId;
	private int statusId;
	private String statusName;
	private int typeId;
	private String typeName;

	public ReimbursementDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReimbursementDTO(int rId, String amount, String submissionDate, String resolutionDate, String description,
			String receipt, int authorId, int resolverId, int statusId, String statusName, int typeId,
			String typeName) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.submissionDate = submissionDate;
		this.resolutionDate = resolutionDate;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.statusName = statusName;
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
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

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + rId;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((resolutionDate == null) ? 0 : resolutionDate.hashCode());
		result = prime * result + resolverId;
		result = prime * result + statusId;
		result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
		result = prime * result + ((submissionDate == null) ? 0 : submissionDate.hashCode());
		result = prime * result + typeId;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (rId != other.rId)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (resolutionDate == null) {
			if (other.resolutionDate != null)
				return false;
		} else if (!resolutionDate.equals(other.resolutionDate))
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		if (submissionDate == null) {
			if (other.submissionDate != null)
				return false;
		} else if (!submissionDate.equals(other.submissionDate))
			return false;
		if (typeId != other.typeId)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [rId=" + rId + ", amount=" + amount + ", submissionDate=" + submissionDate
				+ ", resolutionDate=" + resolutionDate + ", description=" + description + ", receipt=" + receipt
				+ ", authorId=" + authorId + ", resolverId=" + resolverId + ", statusId=" + statusId + ", statusName="
				+ statusName + ", typeId=" + typeId + ", typeName=" + typeName + "]";
	}

}
