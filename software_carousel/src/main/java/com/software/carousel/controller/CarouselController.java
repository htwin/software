package com.software.carousel.controller;

import com.software.carousel.service.CarouselService;
import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;


    @RequestMapping(value = "/{softwareId}",method = RequestMethod.GET)
    public Result findBySoftwareId(@PathVariable String softwareId){
        return new Result(true,"执行成功", StatusCode.OK,carouselService.findBySoftwareId(softwareId));
    }

    /**
     * 轮播图列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(){
        return new Result(true,"执行成功", StatusCode.OK,carouselService.list());
    }

}
