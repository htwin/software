package com.software.user.dao;

import com.software.user.pojo.User;
import com.software.user.pojo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,String> {

    User findByAccount(String Account);

    @Modifying
    @Query(value = "update User set User.thumb = ?1 where User.id = ?2",nativeQuery = true)
    void thumb(String thumbs,String id);

    @Modifying
    @Query(value = "update User set User.download = ?1 where User.id = ?2",nativeQuery = true)
    void downloads(String downloads,String id);

    @Query(value = "select u.id,u.account,u.name,u.sex,u.age,u.createtime,u.updatetime,c.name collegeName from user u,college c where u.college_id = c.id order by u.updatetime desc limit ?,?",nativeQuery = true)
    List<Object[]> userList(int start, int size);


}
