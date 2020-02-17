package com.software.tutorial.mapper;

import com.software.tutorial.pojo.Tutorial;
import com.software.tutorial.pojo.TutorialVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TutorialMapper {

    List<TutorialVo> findAll(@Param("page") Integer page,@Param("size") Integer size);

    TutorialVo findBySoftwareId(@Param("softwareId") String softwareId);

}
