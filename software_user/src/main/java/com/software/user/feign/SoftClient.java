package com.software.user.feign;

import com.software.common.entity.Result;
import com.software.common.entity.StatusCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("soft-application")
public interface SoftClient {
    @RequestMapping(value = "/soft/thumb/{id}", method = RequestMethod.PUT)
    Result thumb(@PathVariable String id);

    @RequestMapping(value = "/soft/updateDownload/{id}", method = RequestMethod.PUT)
    Result updateDownload(@PathVariable String id);
}
