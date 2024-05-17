package com.jsp.agro_springboot.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springboot.entity.Comments;
import com.jsp.agro_springboot.entity.Post;
import com.jsp.agro_springboot.repo.CommentRepo;
import com.jsp.agro_springboot.repo.PostRepo;

@Repository
public class CommentDao {
	@Autowired
	private CommentRepo crepo;
	@Autowired
	private PostDao pdao;
	@Autowired
	private PostRepo prepo;
	
	public Comments saveComment(Comments comments) {
		return crepo.save(comments);
	}

	public Comments deleteComment(String cid) {
		 List<Post> posts = prepo.findAll();
		    for (Post post : posts) {
		        List<Comments> comments = post.getComment();
		        Iterator<Comments> iterator = comments.iterator();
		        while (iterator.hasNext()) {
		            Comments comment = iterator.next();
		            if (comment.getId() == cid) {
		               iterator.remove();
		                pdao.updatePost(post);
		                comment.setUser(null);
		                crepo.deleteById(cid);
		                return comment;
		            }
		        }
		    }
		    return null;
	}

}
