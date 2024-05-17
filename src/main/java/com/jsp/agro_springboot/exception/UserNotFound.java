package com.jsp.agro_springboot.exception;

public class UserNotFound extends RuntimeException{
	
	private String msg="user not found";

	public String getMsg() {
		return msg;
	}

	public UserNotFound() {
		super();
	}

	public UserNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
