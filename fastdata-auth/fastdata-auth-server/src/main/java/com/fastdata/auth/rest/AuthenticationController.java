package com.fastdata.auth.rest;

import com.fastdata.auth.service.IAuthenticationService;
import com.fastdata.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 2:05 AM
 * @Version: 1.0
 * @Description:
 **/
@RestController
@Api("auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @ApiOperation(value = "Permission verify", notes = "check if the user has the right permission to access the url and method")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "url", value = "visited url", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "method", value = "visited method", required = true, dataType = "string")
    })
    @ApiResponses(@ApiResponse(code = 200, message = "success", response = Result.class))
    @PostMapping(value = "/auth/permission")
    public Result decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return Result.success(decide);
    }
}
