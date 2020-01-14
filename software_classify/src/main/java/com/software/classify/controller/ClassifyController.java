package com.software.classify.controller;

import com.software.classify.service.ClassifyService;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classify")
@Api(tags = "类别接口")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "查询类别列表")
    public Result list(){
        return new Result(true,"执行成功", StatusCode.OK,classifyService.list());
    }

}
