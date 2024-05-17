package com.jsp.agro_springboot.exception;

public class EmailNotSendException extends RuntimeException{
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public EmailNotSendException(String msg) {
		super();
		this.msg = msg;
	}

	public EmailNotSendException() {
		super();
	}
	
}
