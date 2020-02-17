package com.software.admin.service;

import com.software.admin.dao.AdminDao;
import com.software.admin.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BCryptPasswordEncoder encode;

    public Admin findByUsernameAndPassword(String username,String password){

        Admin admin = adminDao.findByUsername(username);

        if(admin != null){
            System.out.println(encode.encode(password));
            //判断密码是否正确  忽略密码加密
            if(encode.matches(password,admin.getPassword())){
                return admin;
            }
        }
        return null;

    }

}
