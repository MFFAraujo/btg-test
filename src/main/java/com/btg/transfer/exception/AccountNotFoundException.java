package com.btg.transfer.exception;

public class AccountNotFoundException extends RuntimeException {

	private String message;
	
	private static final long serialVersionUID = 1L;
	
	public AccountNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
