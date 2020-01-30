package com.software.user.controller;

import com.software.common.entity.PageResult;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.common.util.JwtUtil;
import com.software.user.pojo.User;
import com.software.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
            return new Result(true,"登录成功", StatusCode.OK,map);
        }

        return new Result(false,"用户名或密码错误", StatusCode.ERROR);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    @ApiOperation(value = "用户列表")
    public Result search(@PathVariable int page,@PathVariable int size,@RequestBody User user){
        Page pageResult = userService.search(page, size, user);



        return new Result(true,"查询成功",StatusCode.OK,new PageResult<>(pageResult.getTotalElements(),pageResult.getContent()));
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


}
