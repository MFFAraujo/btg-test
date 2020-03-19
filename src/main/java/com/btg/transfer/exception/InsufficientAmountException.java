package com.btg.transfer.exception;

public class InsufficientAmountException extends RuntimeException {

	private String message;
	
	private static final long serialVersionUID = 1L;
	
	public InsufficientAmountException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
