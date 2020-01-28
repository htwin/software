package com.software.classify.controller;

import com.software.classify.pojo.Classify;
import com.software.classify.service.ClassifyService;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classify")
@Api(tags = "类别接口")
@CrossOrigin
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "查询类别列表")
    public Result list(){
        return new Result(true,"执行成功", StatusCode.OK,classifyService.list());
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "新增类别")
    public Result add(@RequestBody Classify classify){
        classifyService.add(classify);
        return new Result(true,"新增成功", StatusCode.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "根据id删除类别")
    public Result delete(@PathVariable String id){
        classifyService.delete(id);
        return new Result(true,"删除成功", StatusCode.OK);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ApiOperation(value = "修改类别")
    public Result update(@RequestBody Classify classify){
        classifyService.update(classify);
        return new Result(true,"修改成功", StatusCode.OK);
    }
}
