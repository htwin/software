package com.software.comment.dao;

import com.software.comment.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,String> {
}
