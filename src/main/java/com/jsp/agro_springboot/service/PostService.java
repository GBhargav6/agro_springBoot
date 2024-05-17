package com.jsp.agro_springboot.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springboot.dao.PostDao;
import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.exception.UserNotFound;
import com.jsp.agro_springboot.util.ResponseStructure;

@Service
public class PostService {
	
	@Autowired
	private PostDao pdao;
	@Autowired
	private UserDao udao;

	public ResponseEntity<ResponseStructure<Post>> savePost(String uid, MultipartFile file, String caption,
			String location) throws IOException {
		User db = udao.fetchById(uid);
		if(db!=null) {
			Post post=new Post();
			Image image=new Image();
			image.setName(file.getOriginalFilename());
			image.setImg(file.getBytes());
			post.setImage(image);
			post.setCaption(caption);
			post.setLocation(location);
			post.setDateTime(LocalDateTime.now());
			Post p=pdao.savePost(post);
			List<Post>list=new ArrayList<Post>();
			list.add(p);
			list.addAll(db.getPost());
			db.setPost(list);
			udao.updateUser(db);
			ResponseStructure<Post> m=new ResponseStructure<Post>();
			m.setData(p);
			m.setMsg("found successfully...");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Post>>( m,HttpStatus.CREATED);
		}
		else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Post>> fetchById(String uid) {
		Post db=pdao.fetchById(uid);
		if(db!=null) {
			ResponseStructure<Post> m=new ResponseStructure<Post>();
			m.setData(db);
			m.setMsg("fetched successfulley...");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Post>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound();
		}
	}

	public ResponseEntity<ResponseStructure<Post>> deleteById(String imageId) {
		Post db=pdao.deletePost(imageId);
		if(db!=null) {
			ResponseStructure<Post> m=new ResponseStructure<Post>();
			m.setData(db);
			m.setMsg("successfully deleted post...");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Post>>(m,HttpStatus.FOUND);
		}
		return null;
	}

}
