package com.jsp.agro_springboot.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.repo.ImageRepo;

@Repository
public class ImageDao {
	
	@Autowired
	private UserDao dao;
	@Autowired
	private ImageRepo imageRepo;
	

	public Image saveImage(Image image) {
		return imageRepo.save(image);
	}

	public Image fetchImage(String id) {
		 Optional<Image> db = imageRepo.findById(id);
		 if(db!=null) {
			return db.get();
		 }
		 else {
			 return null;
		 }	
	}

	public String id(String id) {
		Optional<Image> db = imageRepo.findById(id);
		if(db.isPresent()) {
			return id;
		}
		return null;
	}

	public Image updateImage(String id, MultipartFile file) throws IOException {
		Image image=new Image();
		image.setImg(file.getBytes());
		image.setName(file.getOriginalFilename());
		Optional<Image> db = imageRepo.findById(id);
		Image data = db.get();
		if(db.isPresent()) {
			if(image.getId()==null) {
				image.setId(data.getId());
			}
			if(image.getImg()==null) {
				image.setImg(data.getImg());
			}
			if(image.getName()==null) {
				image.setName(data.getName());
			}
			return imageRepo.save(image);
		}
		else {
			return null;
		}
	}

	public Image deleteImage(String id) {
		Optional<Image> db = imageRepo.findById(id);
		if(db.isPresent()) {
			User udb = dao.fetchUserByImg(db.get());
			udb.setImage(null);
			dao.updateUser(udb);
			imageRepo.deleteById(id);
			return db.get();
		}
		return null;
	}
	
	
	
}
