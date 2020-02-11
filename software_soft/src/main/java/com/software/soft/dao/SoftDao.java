package com.software.soft.dao;

import com.software.soft.pojo.Soft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SoftDao extends JpaRepository<Soft,String>,JpaSpecificationExecutor<Soft>{

    Page<Soft> findByClassifyId(String classifyId, Pageable pageable);

    @Query(value = "select * from soft where ishot=1",nativeQuery = true)
    Page<Soft> findHotList(Pageable pageable);

    @Query(value = "select * from soft order by createtime desc",nativeQuery = true)
    Page<Soft> findNewList(Pageable pageable);

    @Modifying
    @Query(value = "update soft set thumb=thumb+1 where id =?",nativeQuery = true)
    void updateThumb( String id);

    @Query(value = "select * from soft where id not in( select software_id from tutorial)",nativeQuery = true)
    List<Soft> findNoTutorial();
    @Modifying
    @Query(value = "update soft set download=download+1 where id =?",nativeQuery = true)
    void updateDownload(String id);

    @Query(value = "select * from soft where id in (select soft_id from user_soft_download where user_id = ?)",nativeQuery = true)
    List<Soft> findUserDownload(String userId);

    @Query(value = "select * from soft where id in (select soft_id from user_soft_thumb where user_id = ?)",nativeQuery = true)
    List<Soft> findUserThumb(String userId);
}
