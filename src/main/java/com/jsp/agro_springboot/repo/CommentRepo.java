package com.jsp.agro_springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro_springboot.entity.Comments;

public interface CommentRepo extends JpaRepository<Comments, String>{

}
