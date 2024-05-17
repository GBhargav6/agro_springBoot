package com.jsp.agro_springboot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.service.PostService;
import com.jsp.agro_springboot.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class PostController {
	@Autowired
	private PostService service;
	
	@PostMapping("/savepost")
	public ResponseEntity<ResponseStructure<Post>>savePost(String uid,MultipartFile file,String caption,String location) throws IOException{
		return service.savePost(uid,file,caption,location);
	}
	
	@GetMapping("/fetchpost")
	public ResponseEntity<ResponseStructure<Post>> fetchById(@RequestParam String uid){
		return service.fetchById(uid);
	}
	@DeleteMapping("/deletePost")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@RequestParam String id){
		return service.deleteById(id);
	}
}
	