package com.jsp.agro_springboot.exception;

public class MethodArgumentNotValidException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MethodArgumentNotValidException(String message) {
		super();
		this.message = message;
	}

	public MethodArgumentNotValidException() {
		super();
	}
	
}
