package com.jsp.agro_springboot.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springboot.entity.Comments;
import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.repo.PostRepo;
import com.jsp.agro_springboot.repo.UserRepo;

@RestController
public class PostDao {
	
	@Autowired
	private PostRepo prepo;
	@Autowired
	private UserDao udao;
	@Autowired
	private UserRepo urepo;
	@Autowired
	private ImageDao idao;

	public Post savePost(Post post) {
	
		return prepo.save(post);
	}

	public Post fetchById(String uid) {
		Optional<Post> db = prepo.findById(uid);
		if(db!=null) {
			return db.get();
		}
		else {
			return null;
		}		
	}
	public Post deletePost(String id) {
		List<User> db = urepo.findAll();
		for(User user:db) {
			List<Post> posts=user.getPost();
			Iterator<Post> iterator = posts.iterator();
			while(iterator.hasNext()) {
				Post post = iterator.next();
				if(post.getId()==id) {
					iterator.remove();
					udao.updateUser(user);
					prepo.deleteById(id);
					idao.deleteImage(id);
					return post;
				}
			}
		}
		return null;
	}

	public Post updatePost(Post post){
		Optional<Post> db = prepo.findById(post.getId()); 
		if(db.isPresent()) {
			Post data = db.get();
			if(post.getComment()==null) {
				post.setComment(data.getComment());
			}
			if(post.getCaption()==null) {
				post.setCaption(data.getCaption());
			}
			if(post.getLocation()==null) {
				post.setLocation(data.getLocation());
			}
			if(post.getDateTime()==null) {
				post.setDateTime(data.getDateTime());
			}
			return prepo.save(data);
		}
		else {
			return null;
		}
	}


}
