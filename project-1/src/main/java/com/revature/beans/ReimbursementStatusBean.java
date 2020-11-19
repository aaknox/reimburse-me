package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursements_status")
public class ReimbursementStatusBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//fields
	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int statusId;
	
	@Column(name = "status_name")
	private String statusName;
	
	public ReimbursementStatusBean() {
		// TODO Auto-generated constructor stub
	}

}
