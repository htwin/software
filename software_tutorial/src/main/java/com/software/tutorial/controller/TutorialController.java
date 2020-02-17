package com.software.tutorial.controller;

import com.software.common.entity.PageResult;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import com.software.tutorial.pojo.Tutorial;
import com.software.tutorial.service.TutorialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutorial")
@Api(tags = "教程模块")
@CrossOrigin
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @RequestMapping(value = "/{softwareId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据软件id查询该软件教程信息")
    @ApiImplicitParam(name = "softwareId",value = "软件id")
    public Result findBySoftwareId(@PathVariable String softwareId){
        return new Result(true,"执行成功", StatusCode.OK,tutorialService.findBySoftwareId(softwareId));
    }

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "根据教程id查询该软件教程信息")
    @ApiImplicitParam(name = "softwareId",value = "软件id")
    public Result findById(@PathVariable String id){
        return new Result(true,"执行成功", StatusCode.OK,tutorialService.findById(id));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加教程")
    public Result add(@RequestBody Tutorial tutorial){
        boolean success = tutorialService.add(tutorial);

        if(success){
            return new Result(true,"添加成功",StatusCode.OK);
        }else{
            return new Result(false,"失败！！该软件下可能已经有教程",StatusCode.ERROR);
        }


    }

    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "根据教程id删除教程")
    public Result deleteById(@PathVariable String id){
        tutorialService.deleteById(id);
        return new Result(true,"删除成功",StatusCode.OK);
    }

    @RequestMapping(value = "/deleteBySoftwareId/{softwareId}",method = RequestMethod.POST)
    @ApiOperation(value = "根据软件id删除教程")
    public Result deleteBySoftwareId(@PathVariable String softwareId){
        tutorialService.deleteBySoftwareId(softwareId);
        return new Result(true,"删除成功",StatusCode.OK);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ApiOperation(value = "修改教程")
    public Result update(@RequestBody Tutorial tutorial){
        tutorialService.update(tutorial);
        return new Result(true,"修改成功",StatusCode.OK);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    @ApiOperation(value = "教程教程==列表")
    public Result search(@PathVariable int page,@PathVariable int size,@RequestBody Tutorial tutorial){

        return new Result(true,"查询成功",StatusCode.OK,tutorialService.search(page, size, tutorial));
    }



}
