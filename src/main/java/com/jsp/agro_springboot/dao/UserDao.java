package com.jsp.agro_springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepo repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}

	public User fetchById(String id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		return null;
	}

	public User updateUser(User user) {
		Optional<User> db = repo.findById(user.getId());
		if(db!=null) {
			if(user.getFirstName()!=null) {
				db.get().setFirstName(user.getFirstName());
			}
			if(user.getLastName()!=null) {
				db.get().setLastName(user.getLastName());
			}
			if(user.getEmail()!=null) {
				db.get().setEmail(user.getEmail());
			}
			if(user.getPwd()!=null) {
				db.get().setPwd(user.getPwd());
			}
			
			if(user.getAddress()!=null) {
				db.get().setAddress(user.getAddress());
			}
			if(user.getUsertype()!=null) {
				db.get().setUsertype(user.getUsertype());
			}
			if(user.getImage()!=null) {
				db.get().setImage(user.getImage());
			}
			return repo.save(db.get());
		}
		else {
			return null;
		}
	}
	
	public User deleteUser(String id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
		}
		return null;
	}

	public User fetchByEmail(String email) {
		User db = repo.fetchByEmail(email);
		if(db!=null) {
			return db;
		}else {
			return null;
		}
	}

	public User fetchUserByImg(Image image) {
		
		return repo.fetchUserByImg(image);
	}

	public List<User> fetchAllUser() {
		
		return repo.findAll();
	}
	


	
}
