package com.revature.util;


public class ERSException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ERSException() {
		super();
	}
	
	public ERSException(String message) {
		super(message);
	}
	
	public ERSException(Throwable cause) {
		super(cause);
	}
	
	public ERSException(String message, Throwable cause) {
		super(message, cause);
	}
}
