package com.software.user.controller;

import com.software.common.entity.PageResult;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.common.util.IdWorker;
import com.software.common.util.JwtUtil;
import com.software.user.pojo.User;
import com.software.user.pojo.UserSoftThumb;
import com.software.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IdWorker idWorker;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody User user){

        //判断实体相关属性是否为空   ---暂时省略

        User loginUser = userService.findByAccountAndPassword(user.getAccount(), user.getPassword());

        if(loginUser != null){
            //生成token
            String token = jwtUtil.createJWT(loginUser.getId(), loginUser.getName(), "user");
            Map map = new HashMap();
            map.put("token",token);//token
            map.put("name",loginUser.getName());//姓名
            map.put("id",loginUser.getId());
            return new Result(true,"登录成功", StatusCode.OK,map);
        }

        return new Result(false,"用户名或密码错误", StatusCode.ERROR);
    }


    /*@RequestMapping(value = "/thumb/{thumbs}/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "点赞软件")
    public Result thumb(@PathVariable String thumbs,@PathVariable String id){
        userService.thumb(thumbs,id);
        return new Result(true,"点赞成功",StatusCode.OK);
    }*/

    @RequestMapping(value = "/thumb/{userId}/{softId}",method = RequestMethod.POST)
    @ApiOperation(value = "点赞软件")
    public Result thumb(@PathVariable String userId,@PathVariable String softId){
        userService.thumb(userId,softId);
        return new Result(true,"点赞成功",StatusCode.OK);
    }

    @RequestMapping(value = "/getUserSoftThu/{userId}/{softId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id和软件id获取实体对象")
    public Result getUserSoftThu(@PathVariable String userId,@PathVariable String softId){
        UserSoftThumb realEntity = userService.getUserSoftThu(userId,softId);
        return new Result(true,"获取成功",StatusCode.OK,realEntity);
    }

    @RequestMapping(value = "/downloads/{userId}/{softId}",method = RequestMethod.POST)
    @ApiOperation(value = "更新用户下载软件列表")
    public Result downloads(@PathVariable String userId,@PathVariable String softId){
        userService.downloads(userId,softId);
        return new Result(true,"下载成功",StatusCode.OK);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    @ApiOperation(value = "用户列表")
    public Result search(@PathVariable int page,@PathVariable int size,@RequestBody User user){
        try {
            PageResult pageResult = userService.search(page, size, user);
            return new Result(true,"查询成功",StatusCode.OK,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"系统异常",StatusCode.ERROR);
        }

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户")
    public Result add(@RequestBody User user){
        userService.add(user);
        return new Result(true,"添加成功",StatusCode.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "根据id删除用户")
    public Result deleteById(@PathVariable String id){
        userService.deleteById(id);
        return new Result(true,"删除成功",StatusCode.OK);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户信息")
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result(true,"修改成功",StatusCode.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询用户")
    public Result findById(@PathVariable String id){
        return new Result(true,"查询成功",StatusCode.OK, userService.findById(id));
    }


    //批量导入用户
    @RequestMapping(value = "/importUsers",method = RequestMethod.POST)
    public Result importUsers(MultipartFile file) throws IOException {



        userService.importUsers(file);
        return new Result(true,"导入成功",StatusCode.OK);
    }

    //导出模板
    @RequestMapping(value = "/exportTemplate",method = RequestMethod.GET)
    public void exportTemplate() throws IOException {
        userService.exportTemplate();
    }


}
