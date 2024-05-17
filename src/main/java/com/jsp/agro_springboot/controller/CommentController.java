package com.jsp.agro_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springboot.dao.PostDao;
import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.Comments;
import com.jsp.agro_springboot.service.CommentService;
import com.jsp.agro_springboot.util.ResponseStructure;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class CommentController {
	@Autowired
	private UserDao udao;
	@Autowired
	private PostDao pdao;
	@Autowired
	private CommentService service;
	
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comments>> saveComment(@RequestParam String uid,@RequestParam String pid,@RequestParam String message){
		return service.saveComment(uid,pid,message);
	}
	@DeleteMapping("/deleteComment")
	public ResponseEntity<ResponseStructure<Comments>> deleteComment(@RequestParam String cid){
		return service.deleteComment(cid);
	}
}
