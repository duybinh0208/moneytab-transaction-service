package com.moneytab.service;

import java.util.List;

import com.moneytab.exception.TransactionAlreadyExist;
import com.moneytab.exception.TransactionNotFound;
import com.moneytab.model.Transaction;
import com.moneytab.request.TransactionRequest;

/**
 * @author ble
 *
 */
public interface TransactionService {

	void insertTransaction(TransactionRequest request) throws TransactionAlreadyExist;
	
	void updateTransaction(TransactionRequest request) throws TransactionNotFound;
	
	Transaction findTransactionById(long transactionId);
	
	List<Double> findTransactionByType(String type);
	
	double sumTransactionById(long parentId);
	
}
