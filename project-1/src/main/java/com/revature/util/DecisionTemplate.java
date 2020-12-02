package com.revature.util;

public class DecisionTemplate {

	//fields
	private int rId;
	private String amount;
	private int statusId;
	private String statusName;
	private int resolverId;
	
	public DecisionTemplate() {
		// TODO Auto-generated constructor stub
	}

	public DecisionTemplate(int rId, String amount, int statusId, String statusName, int resolverId) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.statusId = statusId;
		this.statusName = statusName;
		this.resolverId = resolverId;
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

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	@Override
	public String toString() {
		return "DecisionTemplate [rId=" + rId + ", amount=" + amount + ", statusId=" + statusId + ", statusName="
				+ statusName + ", resolverId=" + resolverId + "]";
	}
	
	
}
