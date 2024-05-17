package com.jsp.agro_springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro_springboot.entity.Image;
import com.jsp.agro_springboot.entity.User;

public interface ImageRepo extends JpaRepository<Image, String> {
	
	@Query("select a from User a where a.image=?1")
	User fetchByImage(Image image);
	

}
