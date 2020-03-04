package com.software.user.mapper;

import com.software.user.pojo.User;
import com.software.user.pojo.UserSoftThumb;
import com.software.user.pojo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserVo> search(@Param("page") Integer page,@Param("size") Integer size,@Param("user") User user);

    long total(@Param("user") User user);

    void update(@Param("user") User user);

    void saveThumb(@Param("thumb") UserSoftThumb thumb);
}
