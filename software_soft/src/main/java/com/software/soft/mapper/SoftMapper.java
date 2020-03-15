package com.software.soft.mapper;


import com.software.soft.pojo.ClassifySoft;
import com.software.soft.pojo.Soft;
import com.software.soft.pojo.UserSoftDownload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SoftMapper {
    
    List<ClassifySoft> classifySoft();

    List<UserSoftDownload> findUserDownload(@Param("userId") String userId);

    void doRate(@Param("userSoftDownload") UserSoftDownload userSoftDownload);

    void updateScore(@Param("softId") String softId,@Param("score") float score);

    List<Soft> findUserThumb(@Param("userId") String userId);
}
