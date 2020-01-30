package com.software.comment.service;

import com.software.comment.dao.CommentDao;
import com.software.comment.pojo.Comment;
import com.software.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {


    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    public Comment addComment(Comment comment){

        comment.setId(idWorker.nextId()+"");
        comment.setCreatetime(new Date());
        comment.setUpdatetime(new Date());


        return commentDao.save(comment);
    }

    public Page search(String softwareId,String parentId,int page,int size){

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return commentDao.findBySoftwareIdAndParentId(softwareId,parentId,pageRequest);

    }

}
