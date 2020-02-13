package com.software.statistics.controller;

import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.statistics.pojo.CollegeVo;
import com.software.statistics.pojo.SoftVo;
import com.software.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@CrossOrigin
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/college",method = RequestMethod.GET)
    public Result college(){
        List<CollegeVo> college = null;
        try {
            college = statisticsService.college();
            return new Result(true,"操作成功", StatusCode.OK,college);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"操作失败", StatusCode.ERROR);
        }

    }

    @RequestMapping(value = "/software",method = RequestMethod.GET)
    public Result software(){
        List<SoftVo> softVos = null;
        try {
            softVos = statisticsService.software();
            return new Result(true,"操作成功", StatusCode.OK,softVos);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"操作失败", StatusCode.ERROR);
        }

    }

}
