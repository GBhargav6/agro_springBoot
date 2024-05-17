package com.jsp.agro_springboot.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro_springboot.dao.CommentDao;
import com.jsp.agro_springboot.dao.PostDao;
import com.jsp.agro_springboot.dao.UserDao;
import com.jsp.agro_springboot.entity.Comments;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.entity.User;
import com.jsp.agro_springboot.exception.UserNotFound;
import com.jsp.agro_springboot.util.ResponseStructure;

@Service
public class CommentService {
	@Autowired
	private UserDao udao;
	@Autowired
	private PostDao pdao;
	@Autowired
	private CommentDao cdao;

	public ResponseEntity<ResponseStructure<Comments>> saveComment(String uid,String pid,String message){
		 Post db = pdao.fetchById(pid);
		if(db!=null) {
			 User db1 = udao.fetchById(uid);
			 if(db1!=null) {
				 Comments c=new Comments();
				 c.setMsg(message);
				 c.setUser(db1);
				 Comments comment=cdao.saveComment(c);
				 List<Comments> list = new ArrayList<Comments>();
				 list.add(comment);
				 list.addAll(db.getComment());
				 db.setComment(list);
				 pdao.updatePost(db);
				 ResponseStructure<Comments> m=new ResponseStructure<Comments>();
				 m.setData(comment);
				 m.setMsg("comment updated successfully..");
				 m.setStatus(HttpStatus.CREATED.value());
				 return new ResponseEntity<ResponseStructure<Comments>>(m,HttpStatus.CREATED);
			 }
			 else {
				 throw new UserNotFound();
			 }
		}
		else {
			throw new UserNotFound();
		}
		
	}

	public ResponseEntity<ResponseStructure<Comments>> deleteComment(String cid) {
		Comments db = cdao.deleteComment(cid);
		if(db!=null) {
			ResponseStructure<Comments> m= new ResponseStructure<Comments>();
			m.setData(db);
			m.setMsg("deleted successfully");
			m.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Comments>>(m,HttpStatus.GONE);
		}
		else {
			throw new UserNotFound();
		}
		
	}
	
}
