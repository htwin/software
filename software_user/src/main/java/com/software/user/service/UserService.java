package com.software.user.service;

import com.software.common.util.IdWorker;
import com.software.user.dao.UserDao;
import com.software.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

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

    //新增普通用户
    public void add(User user){
        user.setId(idWorker.nextId()+"");
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());

        userDao.save(user);
    }

    //删除用户
    public void deleteById(String id){
        userDao.deleteById(id);
    }

    //修改普通用户
    public void update(User user){
        user.setUpdatetime(new Date());
        userDao.save(user);
    }

    //查询用户列表，可附带条件  (姓名)    user:条件
    public Page search(int page, int size, User user){

        //封装条件   查询


        //分页查询
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(pageRequest);


    }

}
