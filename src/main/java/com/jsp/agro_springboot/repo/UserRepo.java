package com.jsp.agro_springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.entity.User;


public interface UserRepo extends JpaRepository<User, String>{
	
	@Query("select a from User a where a.email=?1")
	public abstract User fetchByEmail(String email);

	@Query("select a from User a where a.image=?1")
	public abstract User fetchUserByImg(Image image);

	@Query("select a from User a where a.post=?1")
	public abstract User fetcUserByPost(Post post);
	

}
