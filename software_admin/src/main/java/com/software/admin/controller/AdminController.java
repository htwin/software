package com.software.admin.controller;

import com.software.admin.pojo.Admin;
import com.software.admin.service.AdminService;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.common.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "管理员模块")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "管理员登录")
    public Result login(@RequestBody Admin admin){

        Admin loginAdmin = adminService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());

        if(loginAdmin != null){

            //登陆成功 生成token
            String token = jwtUtil.createJWT(loginAdmin.getId(), loginAdmin.getUsername(), "admin");

            Map map = new HashMap();

            //将token与用户名返回给前端
            map.put("token",token);
            map.put("username",loginAdmin.getUsername());

            return new Result(true,"登录成功", StatusCode.OK,map);
        }
        return new Result(false,"用户名或密码错误", StatusCode.OK);

    }

}
