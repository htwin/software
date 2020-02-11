package com.software.college.controller;

import com.software.college.service.CollegeService;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/college")
@CrossOrigin
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(){
        return new Result(true,"操作成功", StatusCode.OK,collegeService.list());
    }

}
