package com.jsp.agro_springboot.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro_springboot.dao.EquipmentDao;
import com.jsp.agro_springboot.dao.PaymentHistoryDao;
import com.jsp.agro_springboot.dao.RentalDao;
import com.jsp.agro_springboot.entity.Equipment;
import com.jsp.agro_springboot.entity.PaymentHistory;
import com.jsp.agro_springboot.entity.Rental;
import com.jsp.agro_springboot.exception.EquipmentNameNotFound;
import com.jsp.agro_springboot.util.ResponseStructure;

@Service
public class RentalService {
	@Autowired
	private RentalDao rdao;
	@Autowired
	private EquipmentDao edao;
	@Autowired
	private PaymentHistoryDao pdao;

	public ResponseEntity<ResponseStructure<Rental>> saveRental(String eid, Rental rental) {
	    Equipment equipment = edao.fetchByIdEqp(eid);
		ResponseStructure<Rental>m=new ResponseStructure<Rental>();
		 LocalDateTime startTime = rental.getStartTime();
		 LocalDateTime endTime = rental.getEndTime();
		 if(startTime.isAfter(endTime)) {
			 throw new IllegalArgumentException("Rental Start Time Cannot be after End Time");
		 }
		 if (equipment != null) {
		        Duration duration = Duration.between(startTime, endTime);
		        rental.setEquipment(equipment);
		        long hours = duration.toHours();
		        double amount = equipment.getCostPerHour() * hours;
		        PaymentHistory payment = new PaymentHistory();
		        payment.setAmount(amount);
		        rental.setPayment(payment);
		        pdao.savePayment(payment);
		        Rental db1 = rdao.saveRental(rental);
		        m.setData(db1);
		        m.setMsg("Rental Details Saved Successfully.");
		        m.setStatus(HttpStatus.OK.value());
		        return new ResponseEntity<ResponseStructure<Rental>>(m, HttpStatus.OK);
		 }
		 else {
		        throw new EquipmentNameNotFound();
		 }



	}

}
