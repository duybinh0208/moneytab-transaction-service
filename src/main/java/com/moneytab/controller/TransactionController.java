package com.moneytab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moneytab.exception.TransactionAlreadyExist;
import com.moneytab.exception.TransactionNotFound;
import com.moneytab.model.Transaction;
import com.moneytab.request.TransactionRequest;
import com.moneytab.response.SumTransactionResponse;
import com.moneytab.response.TransactionResponse;
import com.moneytab.service.TransactionService;

/**
 * @author ble
 *
 */
@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	@PostMapping(path = "/transactionservice/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public void addTransaction(@RequestBody TransactionRequest request) throws TransactionAlreadyExist {
		transactionService.insertTransaction(request);
	}
	
	@PutMapping(path = "/transactionservice/transaction/{transaction_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void updateTransaction(@PathVariable("transaction_id") long transactionId, @RequestBody TransactionRequest request) throws TransactionNotFound {
		request.setTransaction_id(transactionId);
		transactionService.updateTransaction(request);
	}
	
	@GetMapping(path = "/transactionservice/transaction/{transaction_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public TransactionResponse findTransactionById(@PathVariable("transaction_id") long transactionId) throws TransactionNotFound {
		Transaction transaction = transactionService.findTransactionById(transactionId);
		return new TransactionResponse(transaction);
	}
	
	@GetMapping(path = "/transactionservice/transaction/types/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Double> findTransactionByType(@PathVariable("type") String type) {
		return transactionService.findTransactionByType(type);
	}
	
	@GetMapping(path = "/transactionservice/transaction/sum/{parent_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public SumTransactionResponse sumTransactionById(@PathVariable("parent_id") long parentId) {
		double sum = transactionService.sumTransactionById(parentId);
		return new SumTransactionResponse(sum);
	}

}
