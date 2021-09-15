package com.fastdata.sysadmin.organization.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.form.UserForm;
import com.fastdata.sysadmin.organization.entity.form.UserQueryForm;
import com.fastdata.sysadmin.organization.entity.form.UserUpdateForm;
import com.fastdata.sysadmin.organization.entity.param.UserQueryParam;
import com.fastdata.sysadmin.organization.entity.po.User;
import com.fastdata.sysadmin.organization.service.IUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 2021/9/15 - 23:13
 * @Version: 1.0
 * @description:
 **/

@RestController
@RequestMapping("/user")
@Api("user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "add new user", notes = "add a new user")
    @ApiImplicitParam(name = "userForm", value = "add new user form", required = true, dataType = "UserForm")
    @PostMapping
    public Result add(@Valid @RequestBody UserForm userForm) {
        log.debug("name: {}", userForm);
        User user = userForm.toPo(User.class);
        return Result.success(userService.add(user));
    }

    @ApiOperation(value = "delete user", notes = "delete user by url id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "userID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(userService.delete(id));
    }

    @ApiOperation(value = "update user", notes = "update user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "userID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "userUpdateForm", value = "user entity", required = true, dataType = "UserUpdateForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody UserUpdateForm userUpdateForm) {
        User user = userUpdateForm.toPo(User.class);
        user.setId(id);
        return Result.success(userService.update(user));
    }

    @ApiOperation(value = "get user", notes = "get user info")
    @ApiImplicitParam(paramType = "path", name = "id", value = "userID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id: {}", id);
        return Result.success(userService.get(id));
    }

    @ApiOperation(value = "get user", notes = "query user by unique id (username or mobile)")
    @ApiImplicitParam(paramType = "query", name = "uniqueId", value = "user unique id", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @GetMapping
    public Result query(@RequestParam String uniqueId) {
        log.debug("query with username or mobile: {}", uniqueId);
        return Result.success(userService.getByUniqueId(uniqueId));
    }

    @ApiOperation(value = "search user", notes = "search user by conditions")
    @ApiImplicitParam(name = "userQueryForm", value = "user query params", required = true, dataType = "UserQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody UserQueryForm userQueryForm) {
        log.debug("search with userQueryForm: {}", userQueryForm);
        return Result.success(userService.query(userQueryForm.getPage(), userQueryForm.toParam(UserQueryParam.class)));
    }
}
