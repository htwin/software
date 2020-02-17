package com.software.tutorial.feign;

import com.software.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("soft-application")
public interface SoftClient {
    @RequestMapping(value = "/soft/updateTutorial/{tutorial}/{id}", method = RequestMethod.PUT)
    Result updateTutorial(@PathVariable int tutorial,@PathVariable String id);


}
