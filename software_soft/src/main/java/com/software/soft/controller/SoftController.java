package com.software.soft.controller;

import com.software.common.entity.PageResult;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.soft.service.SoftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soft")
@Api(tags = "软件相关接口")
public class SoftController {

    @Autowired
    private SoftService softService;


    @RequestMapping(value = "/list/{page}/{size}",method = RequestMethod.GET)
    @ApiOperation( value = "查询软件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", defaultValue = "10")
    }
    )
    public Result softList(@PathVariable int page,@PathVariable int size){

         Page pages = softService.getSoftList(page, size);

         return new Result(true,"执行成功", StatusCode.OK,new PageResult<>(pages.getTotalElements(),pages.getContent()));
    }

    @RequestMapping(value = "/{classifyId}/{page}/{size}",method = RequestMethod.GET)
    @ApiOperation( value = "根据类别查询软件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classifyId", value = "类别id"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", defaultValue = "10")
    }
    )
    public Result findByClassifyId(@PathVariable String classifyId,@PathVariable int page,@PathVariable int size){

        Page pages = softService.findByClassifyId(classifyId,page,size);
        return new Result(true,"执行成功", StatusCode.OK,new PageResult<>(pages.getTotalElements(),pages.getContent()));
    }

    @RequestMapping(value = "/hotList/{page}/{size}",method = RequestMethod.GET)
    @ApiOperation( value = "查询热门(推荐)软件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", defaultValue = "10")
    }
    )
    public Result findHotList(@PathVariable int page ,@PathVariable int size){
        Page pages = softService.findHotList(page,size);
        return new Result(true,"执行成功", StatusCode.OK,new PageResult<>(pages.getTotalElements(),pages.getContent()));
    }

    @RequestMapping(value = "/newList/{page}/{size}",method = RequestMethod.GET)
    @ApiOperation( value = "查询最新软件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", defaultValue = "10")
    }
    )
    public Result findNewList(@PathVariable int page ,@PathVariable int size){
        Page pages = softService.findNewList(page,size);
        return new Result(true,"执行成功", StatusCode.OK,new PageResult<>(pages.getTotalElements(),pages.getContent()));
    }

}
