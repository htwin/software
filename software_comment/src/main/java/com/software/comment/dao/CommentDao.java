package com.software.comment.dao;

import com.software.comment.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,String> {

    Page findBySoftwareIdAndParentId(String softwareId, String parentId, Pageable pageable);

}
