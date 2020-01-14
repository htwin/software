package com.software.tutorial.controller;

import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.tutorial.service.TutorialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutorial")
@Api(tags = "教程模块")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @RequestMapping(value = "/{softwareId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据软件id查询该软件教程信息")
    @ApiImplicitParam(name = "softwareId",value = "软件id")
    public Result findBySoftwareId(@PathVariable String softwareId){
        return new Result(true,"执行成功", StatusCode.OK,tutorialService.findBySoftwareId(softwareId));
    }

}
