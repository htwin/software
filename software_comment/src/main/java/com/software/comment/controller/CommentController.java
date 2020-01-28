package com.software.comment.controller;

import com.software.comment.pojo.Comment;
import com.software.comment.service.CommentService;
import com.software.common.entity.PageResult;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
@Api(tags = "评论模块")
@CrossOrigin
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ApiOperation(value = "新增评论")
    public Result addComment(@RequestBody Comment comment){

        commentService.addComment(comment);

        return new Result(true,"评论成功", StatusCode.OK);
    }

    @RequestMapping(value = "/search/{softwareId}/{parentId}/{page}/{size}",method = RequestMethod.GET)
    @ApiOperation(value = "评论列表")
    public Result search(@PathVariable String softwareId,@PathVariable String parentId,@PathVariable  int page,@PathVariable int size){

        Page pageResult = commentService.search(softwareId, parentId, page, size);

        return new Result(true,"查询成功", StatusCode.OK,new PageResult<>(pageResult.getTotalElements(),pageResult.getContent()));
    }


}
