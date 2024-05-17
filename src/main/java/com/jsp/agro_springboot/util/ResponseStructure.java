package com.jsp.agro_springboot.util;

import java.util.List;

import com.jsp.agro_springboot.entity.Equipment;

public class ResponseStructure <T>{
	private String msg;
	private int status;
	private T data;
	private List<T> listdata;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getListdata() {
		return listdata;
	}
	public void setListdata(List<T> listdata) {
		this.listdata = listdata;
	}
	public ResponseStructure(String msg, int status, T data, List<T> listdata) {
		super();
		this.msg = msg;
		this.status = status;
		this.data = data;
		this.listdata = listdata;
	}
	public ResponseStructure() {
		super();
	}
	
	
}
