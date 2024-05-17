package com.jsp.agro_springboot.exception;

public class PasswordMismatchException extends RuntimeException{
	private String msg="password is mismatched";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PasswordMismatchException(String msg) {
		super();
		this.msg = msg;
	}
	
}
