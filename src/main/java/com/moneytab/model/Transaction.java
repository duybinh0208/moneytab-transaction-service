package com.moneytab.model;

import com.moneytab.request.TransactionRequest;

/**
 * @author ble
 *
 */
public class Transaction {

	private long id;
	private long parentId;
	private double amount;
	private String type;
	
	public Transaction() {
		super();
	}

	public Transaction(TransactionRequest request) {
		super();
		this.id = request.getTransaction_id();
		this.parentId = request.getParent_id();
		this.amount = request.getAmount();
		this.type = request.getType();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
