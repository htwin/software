package com.software.statistics.service;

import com.software.common.util.CommonUtils;
import com.software.statistics.mapper.StatisticsMapper;
import com.software.statistics.pojo.CollegeVo;

import com.software.statistics.pojo.SoftVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public List<CollegeVo> college() throws Exception {
        List<CollegeVo> collegeVos = statisticsMapper.collegeNameAndDownload();
        return collegeVos;
    }

    public List<SoftVo> software() throws Exception {
        List<SoftVo> softVos = statisticsMapper.softNameAndDownload();
        return softVos;
    }

}
