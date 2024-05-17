package com.jsp.agro_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springboot.entity.Equipment;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.service.EquipmentService;
import com.jsp.agro_springboot.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class EquipmentController {
	@Autowired
	private EquipmentService service;
	
	@PostMapping("/saveEquipment")
	public ResponseEntity<ResponseStructure<Equipment>>saveEquipment(@RequestParam String uid,@RequestBody Equipment equipment){
		return service.saveEquipment(uid,equipment);
		
	}
	@PutMapping("/updateEqp")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment equipment){
		return service.updateEquipment(equipment);
	}
	@DeleteMapping("/deleteEqp")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam String id){
		return service.deleteEquipment(id);
	}
	@GetMapping("/fetchByIdEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEqp(@RequestParam String id){
		return service.fetchByIdEqp(id);
	}
	@GetMapping("/fetchAllEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchAllEqp(){
		return service.fetchAllEqp();
	}
	
	@GetMapping("/fetchByNameEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(@RequestParam String name){
		return service.fetchByName(name);
	}
	@GetMapping("/fetchByUserId")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByUserId(@RequestParam String uid){
		return service.fetchByUserId(uid);
		
	}
}
