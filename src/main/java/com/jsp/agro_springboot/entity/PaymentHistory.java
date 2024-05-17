package com.jsp.agro_springboot.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.agro_springboot.util.CustomIdGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymenthistory_seq")
	@GenericGenerator(name = "paymenthistory_seq", strategy = "com.jsp.agro_springboot.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "PaymentHistory"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String mode;
	private LocalDateTime paymentTime;
	private double amount;
	@ManyToOne
	private User user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PaymentHistory(String mode, LocalDateTime paymentTime, double amount, User user) {
		super();
		this.mode = mode;
		this.paymentTime = paymentTime;
		this.amount = amount;
		this.user = user;
	}
	public PaymentHistory() {
		super();
	}
	
}
