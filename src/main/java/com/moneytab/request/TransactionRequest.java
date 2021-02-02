/**
 * 
 */
package com.moneytab.request;

import com.moneytab.model.Transaction;

/**
 * @author ble
 *
 */
public class TransactionRequest {

	private long transaction_id;
	private long parent_id;
	private double amount;
	private String type;
	
	public TransactionRequest() {
		super();
	}

	public TransactionRequest(Transaction transaction) {
		super();
		this.transaction_id = transaction.getId();
		this.parent_id = transaction.getParentId();
		this.amount = transaction.getAmount();
		this.type = transaction.getType();
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
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
