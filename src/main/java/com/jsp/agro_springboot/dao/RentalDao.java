package com.jsp.agro_springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springboot.entity.Rental;
import com.jsp.agro_springboot.repo.RentalRepo;

@Repository
public class RentalDao {
	@Autowired
	private RentalRepo rrepo;

	public Rental saveRental(Rental rental) {
		return rrepo.save(rental);
	}
	
}
