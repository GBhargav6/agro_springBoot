package com.jsp.agro_springboot.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jsp.agro_springboot.dao.ImageDao;
import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.exception.UserNotFound;
import com.jsp.agro_springboot.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private UserDao udao;
	@Autowired
	private ImageDao idao;
	
	public ResponseEntity<ResponseStructure<Image>>uploadImage(String uid,MultipartFile file) throws IOException{
		ResponseStructure<Image> m= new ResponseStructure<Image>();
		Image image=new Image();
		User db = udao.fetchById(uid);
		if(db!=null) {
			image.setName(file.getOriginalFilename());
			image.setImg(file.getBytes());
//			Image db1 = idao.saveImage(image);
			db.setImage(image);
			udao.updateUser(db);
			m.setData(image);
			m.setMsg("Profile Pic Uploaded Successfully");
			m.setStatus(HttpStatus.CREATED.value());
			 
			return new ResponseEntity<ResponseStructure<Image>>(m,null,HttpStatus.CREATED.value());
		}
		else {
			throw new UserNotFound();
		}
		

	}

	public ResponseEntity<byte[]> fetchImageById(String id) {
		Image db = idao.fetchImage(id);
		if(db!=null) {
			byte[] imageBytes = idao.fetchImage(id).getImg();
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_JPEG);
		    return new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.OK);
		}
		else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Image>> updateImage(String id, MultipartFile file) throws IOException {
		String id1 = idao.id(id);
		if(id1==id) {
			Image db = idao.updateImage(id, file);
			ResponseStructure<Image> m= new ResponseStructure<Image>();
			if(db!=null) {
				m.setMsg("image updated successfully");
				m.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.OK);
			}
		}
			throw new UserNotFound();
	}

	public ResponseEntity<ResponseStructure<Image>> deleteImage(String id) {
		Image db1 = idao.fetchImage(id);
		if(db1!=null) {
			Image db = idao.deleteImage(id);
			ResponseStructure<Image> m= new ResponseStructure<Image>();
			m.setData(db);
			m.setMsg("deleted Successfully");
			m.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.GONE);
		}
			throw new UserNotFound();
	}
}
