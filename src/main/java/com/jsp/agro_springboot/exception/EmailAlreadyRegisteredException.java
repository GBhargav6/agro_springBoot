package com.jsp.agro_springboot.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{
	
	private String msg="Email is already register";


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public EmailAlreadyRegisteredException(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
