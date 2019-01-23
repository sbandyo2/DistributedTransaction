package com.ibm.exception;

public class TransactionException extends Exception {

	private static final long serialVersionUID = -8845242379503538623L;

	public TransactionException(String message) {
		super(message);
	}
}
