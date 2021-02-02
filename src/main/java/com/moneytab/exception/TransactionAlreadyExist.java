/**
 * 
 */
package com.moneytab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ble
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
@ResponseBody
public class TransactionAlreadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
