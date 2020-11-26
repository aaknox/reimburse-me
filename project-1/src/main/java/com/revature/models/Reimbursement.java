package com.revature.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursements")
public class Reimbursement implements Serializable {
	private static final long serialVersionUID = 1L;
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	private int rId;

	@Column(name = "reimb_amount")
	private BigDecimal amount;

	@Column(name = "reimb_submitted")
	private LocalDateTime submissionDateTime;

	@Column(name = "reimb_resolved")
	private LocalDateTime resolutionDateTime;

	@Column(name = "reimb_description")
	private String description;

	@Column(name = "reimb_receipt")
	private byte[] receipt;

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "reimb_author_id", referencedColumnName = "ers_user_id")
	private User author = new User();

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "reimb_resolver_id", referencedColumnName = "ers_user_id")
	private User resolver = new User();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reimb_status_id", referencedColumnName = "status_id")
	private ReimbursementStatus status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reimb_type_id", referencedColumnName = "type_id")
	private ReimbursementType type;

	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int rId, BigDecimal amount, LocalDateTime submissionDateTime, LocalDateTime resolutionDateTime,
			String description, byte[] receipt, User author, User resolver, ReimbursementStatus status,
			ReimbursementType type) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.submissionDateTime = submissionDateTime;
		this.resolutionDateTime = resolutionDateTime;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(BigDecimal amount, LocalDateTime submissionDateTime, LocalDateTime resolutionDateTime,
			String description, byte[] receipt, User author, User resolver, ReimbursementStatus status,
			ReimbursementType type) {
		super();
		this.amount = amount;
		this.submissionDateTime = submissionDateTime;
		this.resolutionDateTime = resolutionDateTime;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + rId;
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + ((resolutionDateTime == null) ? 0 : resolutionDateTime.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (rId != other.rId)
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (resolutionDateTime == null) {
			if (other.resolutionDateTime != null)
				return false;
		} else if (!resolutionDateTime.equals(other.resolutionDateTime))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
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
				+ ", resolutionDateTime=" + resolutionDateTime + ", description=" + description + ", receipt="
				+ Arrays.toString(receipt) + ", author=" + author + ", resolver=" + resolver + ", status=" + status
				+ ", type=" + type + "]";
	}

}
