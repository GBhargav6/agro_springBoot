package com.jsp.agro_springboot.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.agro_springboot.util.CustomIdGenerator;


@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
	@GenericGenerator(name = "rental_seq", strategy = "com.jsp.agro_springboot.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Rental"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	@ManyToOne(cascade = CascadeType.ALL)
	private Equipment equipment;
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentHistory payment;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public PaymentHistory getPayment() {
		return payment;
	}
	public void setPayment(PaymentHistory payment) {
		this.payment = payment;
	}
	public Rental(LocalDateTime startTime, LocalDateTime endTime, Equipment equipment, PaymentHistory payment) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.equipment = equipment;
		this.payment = payment;
	}
	public Rental() {
		super();
	}
	
	
	
	
	
	
	
}
