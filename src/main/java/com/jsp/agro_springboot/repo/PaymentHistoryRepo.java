package com.jsp.agro_springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro_springboot.entity.PaymentHistory;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, String>{

}
