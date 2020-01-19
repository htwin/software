package com.software.user.dao;

import com.software.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {

    User findByAccount(String Account);

}
