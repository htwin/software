package com.software.soft.controller;

import com.software.common.entity.PageResult;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.common.util.FastDFSUtil;
import com.software.soft.pojo.Soft;
import com.software.soft.service.SoftService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/soft")
@Api(tags = "软件相关接口")
public class SoftController {

    @Autowired
    private SoftService softService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/download",method = RequestMethod.POST)
    @ApiOperation(value = "软件下载")
    public Result download(@RequestBody Soft soft){
        //group1-M00/00/00/wKhihF4hZwmAJoXHAAAXA0yMGTo637.png

        //下载软件需要登录 user权限
        Claims claims = (Claims) request.getAttribute("user_claims");
        if(claims == null){
            return new Result(false,"请登录",StatusCode.ERROR);
        }

        try {
            softService.download(soft);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(true,"下载成功",StatusCode.OK);

    }

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


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id查询软件详情")
    @ApiImplicitParam(name = "id",value = "软件id",required = true)
    public Result findById(@PathVariable String id){
        return new Result(true,"执行成功",StatusCode.OK,softService.findById(id));
    }

    @RequestMapping(value = "/thumb/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "点赞软件，软件的点赞数加一")
    @ApiImplicitParam(name = "id",value = "软件id",required = true)
    public Result thumb(@PathVariable String id){
        softService.thumb(id);
        return new Result(true,"点赞成功",StatusCode.OK);
    }

}
