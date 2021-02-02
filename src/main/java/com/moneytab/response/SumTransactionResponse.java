/**
 * 
 */
package com.moneytab.response;

/**
 * @author ble
 *
 */
public class SumTransactionResponse {
	
	private double amount;

	public SumTransactionResponse() {
	}

	public SumTransactionResponse(double amount) {
		super();
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
