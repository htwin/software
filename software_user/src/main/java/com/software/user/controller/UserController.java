package com.software.user.controller;

import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.common.util.JwtUtil;
import com.software.user.pojo.User;
import com.software.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
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

}
