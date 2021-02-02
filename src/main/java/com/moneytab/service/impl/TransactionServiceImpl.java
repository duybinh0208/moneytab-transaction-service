/**
 * 
 */
package com.moneytab.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moneytab.exception.TransactionAlreadyExist;
import com.moneytab.exception.TransactionNotFound;
import com.moneytab.model.Transaction;
import com.moneytab.request.TransactionRequest;
import com.moneytab.service.TransactionService;

/**
 * @author ble
 *
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	private static final AtomicLong COUNTER = new AtomicLong();
	private static final Map<Long, Transaction> TRANSACTIONS = new HashMap<Long, Transaction>();
	
	public long generateTransactionId() {
		return COUNTER.incrementAndGet();
	}
	
	@Override
	public void insertTransaction(TransactionRequest request) throws TransactionAlreadyExist {
		// Check if the transaction exists?
		long transactionId = request.getTransaction_id();
		if(TRANSACTIONS.containsKey(transactionId)) {
			throw new TransactionAlreadyExist();
		}
		
		// Create the new one
		transactionId = this.generateTransactionId();
		Transaction transaction = new Transaction();
		transaction.setId(transactionId);
		transaction.setAmount(request.getAmount());
		transaction.setParentId(request.getParent_id());
		transaction.setType(request.getType());
		TRANSACTIONS.put(transactionId, transaction);
	}
	
	@Override
	public void updateTransaction(TransactionRequest request) throws TransactionNotFound {
		// Check if the transaction exists?
		long transactionId = request.getTransaction_id();
		if(!TRANSACTIONS.containsKey(transactionId)) {
			throw new TransactionNotFound();
		}
		
		// Update the existing one
		Transaction transaction = TRANSACTIONS.get(transactionId);
		transaction.setAmount(request.getAmount());
		transaction.setParentId(request.getParent_id());
		transaction.setType(request.getType());
	}
	
	@Override
	public Transaction findTransactionById(long transactionId) throws TransactionNotFound {
		// Check if the transaction exists?
		if(!TRANSACTIONS.containsKey(transactionId)) {
			throw new TransactionNotFound();
		}
		
		// Return the result
		return TRANSACTIONS.get(transactionId);
	}
	
	@Override
	public List<Double> findTransactionByType(String type) {
		// Find transactions by a specific type
		List<Transaction> filteredTransactions = TRANSACTIONS.values().parallelStream().filter(transaction -> transaction.getType().equals(type)).collect(Collectors.toList());
		return (List<Double>) filteredTransactions.parallelStream().map(transaction -> transaction.getAmount()).collect(Collectors.toList());
	}
	
	@Override
	public double sumTransactionById(long parentId) {
		// Sum all of transactions by a specific parentId
		List<Transaction> filteredTransactions = TRANSACTIONS.values().parallelStream().filter(transaction -> transaction.getParentId() == parentId).collect(Collectors.toList());
		return filteredTransactions.parallelStream().collect(Collectors.summingDouble(transaction -> transaction.getAmount()));
	}

}
