package com.fastdata.sysadmin.organization.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.form.RoleForm;
import com.fastdata.sysadmin.organization.entity.form.RoleQueryForm;
import com.fastdata.sysadmin.organization.entity.form.RoleUpdateForm;
import com.fastdata.sysadmin.organization.entity.param.RoleQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Role;
import com.fastdata.sysadmin.organization.service.IRoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 2021/9/15 - 22:20
 * @Version: 1.0
 * @description:
 **/

@RestController
@RequestMapping("/role")
@Api("role")
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "add new role", notes = "add a new role")
    @ApiImplicitParam(name = "roleForm", value = "add new role form", required = true, dataType = "RoleForm")
    @PostMapping
    public Result add(@Valid @RequestBody RoleForm roleForm) {
        log.debug("name: {}", roleForm);
        Role role = roleForm.toPo(Role.class);
        return Result.success(roleService.add(role));
    }

    @ApiOperation(value = "delete role", notes = "delete role by url id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "role ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(roleService.delete(id));
    }

    @ApiOperation(value = "update role", notes = "update role info")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "role ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "roleForm", value = "role entity", required = true, dataType = "RoleUpdateForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody RoleUpdateForm roleUpdateForm) {
        Role role = roleUpdateForm.toPo(id, Role.class);
        return Result.success(roleService.update(role));
    }

    @ApiOperation(value = "get role", notes = "get role info")
    @ApiImplicitParam(paramType = "path", name = "id", value = "role ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id: {}", id);
        return Result.success(roleService.get(id));
    }

    @ApiOperation(value = "get all roles", notes = "get all roles")
    @GetMapping(value = "/all")
    public Result get() {
        return Result.success(roleService.getAll());
    }

    @ApiOperation(value = "query role", notes = "query role by user id")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "user id", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @GetMapping(value = "/user/{userId}")
    public Result query(@PathVariable String userId) {
        log.debug("query with userId: {}", userId);
        return Result.success(roleService.query(userId));
    }

    @ApiOperation(value = "search role", notes = "search role by conditions")
    @ApiImplicitParam(name = "roleQueryForm", value = "role query params", required = true, dataType = "RoleQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody RoleQueryForm roleQueryForm) {
        log.debug("query with name: {}", roleQueryForm);
        return Result.success(roleService.query(roleQueryForm.getPage(), roleQueryForm.toParam(RoleQueryParam.class)));
    }

}
