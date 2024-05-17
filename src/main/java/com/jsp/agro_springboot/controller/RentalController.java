package com.jsp.agro_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springboot.entity.Rental;
import com.jsp.agro_springboot.service.RentalService;
import com.jsp.agro_springboot.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class RentalController {
	
	@Autowired
	private RentalService service;
	@PostMapping("/saveRental")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam String eid, @RequestBody Rental rental){
		return service.saveRental(eid,rental);
	}
}
