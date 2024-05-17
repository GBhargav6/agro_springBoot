package com.jsp.agro_springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro_springboot.entity.Post;

public interface PostRepo extends JpaRepository<Post, String>{
	
}
