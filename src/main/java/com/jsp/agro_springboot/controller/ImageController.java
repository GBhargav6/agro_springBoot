package com.jsp.agro_springboot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.service.ImageService;
import com.jsp.agro_springboot.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ImageController {
	@Autowired
	private ImageService service;

	@PostMapping("/saving")
	public ResponseEntity<ResponseStructure<Image>> uploadImage(@RequestParam String uid,@RequestParam("file") MultipartFile file) throws IOException {
		return service.uploadImage(uid, file);
	}
	@GetMapping("/getimagedata")
	public ResponseEntity<byte[]> fetchImageById(@RequestParam String id){
		return service.fetchImageById(id);
	}
	@PutMapping("/updateimg")
	public ResponseEntity<ResponseStructure<Image>> updateImage(@RequestParam String id,@RequestParam MultipartFile file) throws IOException{
		return service.updateImage(id,file);
	}
	@DeleteMapping("/deleting")
	public ResponseEntity<ResponseStructure<Image>> deleteImg(@RequestParam String id){
		return service.deleteImage(id);
	}
	
	
}
