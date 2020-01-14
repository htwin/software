package com.software.comment.controller;

import com.software.comment.pojo.Comment;
import com.software.comment.service.CommentService;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ApiOperation(value = "新增评论")
    public Result addComment(@RequestBody Comment comment){

        commentService.addComment(comment);

        return new Result(true,"评论成功", StatusCode.OK);
    }

}
