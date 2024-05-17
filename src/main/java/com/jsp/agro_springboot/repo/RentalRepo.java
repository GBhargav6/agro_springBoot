package com.jsp.agro_springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro_springboot.entity.Rental;

public interface RentalRepo extends JpaRepository<Rental, String>{

}
