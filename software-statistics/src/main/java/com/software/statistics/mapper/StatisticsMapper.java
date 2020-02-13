package com.software.statistics.mapper;

import com.software.statistics.pojo.CollegeVo;
import com.software.statistics.pojo.SoftVo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface StatisticsMapper{


    List<CollegeVo> collegeNameAndDownload();

    List<SoftVo> softNameAndDownload();
}
