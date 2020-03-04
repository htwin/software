package com.software.user.dao;

import com.software.user.pojo.UserSoftDownload;
import com.software.user.pojo.UserSoftThumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserSoftThumbDao extends JpaRepository<UserSoftThumb,String> {
    UserSoftThumb findByUserIdAndAndSoftId(String userId, String softId);


//    @Query(value = "insert into user_soft_thumb(user_id,soft_id) VALUES(?1,?2)",nativeQuery = true)
//    void add(String userId, String softId);
}
