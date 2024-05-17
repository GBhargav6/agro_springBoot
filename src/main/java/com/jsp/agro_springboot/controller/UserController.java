package com.jsp.agro_springboot.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.service.UserService;
import com.jsp.agro_springboot.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private UserDao dao;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>>saveUser(@RequestBody User user){
		return service.registerUser(user);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	@GetMapping("/getbyid")
	public ResponseEntity<ResponseStructure<User>> getById(@RequestParam String id){
		return service.fetchById(id);
	}
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		return service.fetchAll();
	}
	
	@DeleteMapping("/deletedata")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam String id){
		return service.deleteUser(id);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email,@RequestParam String password){
		return service.loginUser(email,password);
	}
	@GetMapping("/sendotp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return service.sendOtp(email);
	}
	

}
