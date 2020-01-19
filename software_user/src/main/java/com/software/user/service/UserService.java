package com.software.user.service;

import com.software.user.dao.UserDao;
import com.software.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByAccountAndPassword(String account,String password){

        //根据账号查找用户
        User user = userDao.findByAccount(account);

        if(user!=null){

            //判断密码   省略密码加密
            if(user.getPassword().equals(password)){
                return user;
            }

        }
        return null;

    }

}
