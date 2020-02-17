package com.software.user.dao;

import com.software.user.pojo.UserSoftDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface UserSoftDownloadDao extends JpaRepository<UserSoftDownload,String> {
    UserSoftDownload findByUserIdAndAndSoftId(String userId,String softId);

    @Modifying
    @Query(value = "insert into user_soft_download(user_id,soft_id,createtime) VALUES(?1,?2,?3)",nativeQuery = true)
    void add(String userId, String softId,Date createtime);
}
