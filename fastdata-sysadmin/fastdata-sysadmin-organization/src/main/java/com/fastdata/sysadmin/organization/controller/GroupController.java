package com.fastdata.sysadmin.organization.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.form.GroupForm;
import com.fastdata.sysadmin.organization.entity.form.GroupQueryForm;
import com.fastdata.sysadmin.organization.entity.param.GroupQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Group;
import com.fastdata.sysadmin.organization.service.IGroupService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/3/21 11:58 PM
 * @Version: 1.0
 * @Description:
 **/
@RestController
@RequestMapping("/group")
@Api("group")
@Slf4j
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @ApiOperation(value = "add new group", notes = "add new group")
    @ApiImplicitParam(name = "groupForm", value = "form of add new group", required = true, dataType = "GroupForm")
    @PostMapping
    public Result add(@Valid @RequestBody GroupForm groupForm) {
        log.debug("name: {}", groupForm);
        return Result.success(groupService.add(groupForm.toPo(Group.class)));
    }

    @ApiOperation(value = "delete group", notes = "delete group by the id in url")
    @ApiImplicitParam(paramType = "path", name = "id", value = "group ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(groupService.delete(id));
    }

    @ApiOperation(value = "update group", notes = "update group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "group id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "groupForm", value = "group entity", required = true, dataType = "GroupForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody GroupForm groupForm) {
        Group group = groupForm.toPo(Group.class);
        group.setId(id);
        return Result.success(groupService.update(group));
    }

    @ApiOperation(value = "get group", notes = "get group info")
    @ApiImplicitParam(paramType = "path", name = "id", value = "group ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id: {}", id);
        return Result.success(groupService.get(id));
    }

    @ApiOperation(value = "query group", notes = "query group by conditions, simple query")
    @ApiImplicitParam(paramType = "query", name = "name", value = "group name", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "success", response = Result.class)
    )
    @GetMapping
    public Result query(@RequestParam String name) {
        log.debug("query with name: {}", name);
        GroupQueryParam groupQueryParam = new GroupQueryParam();
        groupQueryParam.setName(name);
        return Result.success(groupService.query(groupQueryParam));
    }

    @ApiOperation(value = "search group", notes = "search group by the given conditions")
    @ApiImplicitParam(name = "groupQueryForm", value = "group query params", required = true, dataType = "GroupQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "success", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GroupQueryForm groupQueryForm) {
        log.debug("search with groupQueryForm: {}", groupQueryForm);
        return Result.success(groupService.query(groupQueryForm.toParam(GroupQueryParam.class)));
    }

    @ApiOperation(value = "query group by parent id", notes = "query group by parent id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "group parent ID", required = true, dataType = "string")
    @GetMapping(value = "/parent/{id}")
    public Result search(@PathVariable String id) {
        log.debug("query with parent id: {}", id);
        return Result.success(groupService.queryByParentId(id));
    }
}
