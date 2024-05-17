package com.jsp.agro_springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springboot.entity.PaymentHistory;
import com.jsp.agro_springboot.repo.PaymentHistoryRepo;

@Repository
public class PaymentHistoryDao {
	@Autowired
	private PaymentHistoryRepo prepo;
	
	public PaymentHistory savePayment(PaymentHistory payment) {
		return prepo.save(payment);
	}

}
