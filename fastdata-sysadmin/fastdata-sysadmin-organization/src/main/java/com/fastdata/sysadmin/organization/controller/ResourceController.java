package com.fastdata.sysadmin.organization.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.form.ResourceForm;
import com.fastdata.sysadmin.organization.entity.form.ResourceQueryForm;
import com.fastdata.sysadmin.organization.entity.param.ResourceQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import com.fastdata.sysadmin.organization.service.IResourceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/6/21 1:08 PM
 * @Version: 1.0
 * @Description:
 **/
@RestController
@RequestMapping("/resource")
@Api("resource")
@Slf4j
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @ApiOperation(value = "add new resource", notes = "add new resource")
    @ApiImplicitParam(name = "resourceForm", value = "new resource Form", required = true, dataType = "ResourceForm")
    @PostMapping
    public Result add(@Valid @RequestBody ResourceForm resourceForm) {
        log.debug("name: {}", resourceForm);
        Resource resource = resourceForm.toPo(Resource.class);
        return Result.success(resourceService.add(resource));
    }

    @ApiOperation(value = "delete resource", notes = "delete id by url")
    @ApiImplicitParam(paramType = "path", name = "id", value = "resource ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(resourceService.delete(id));
    }

    @ApiOperation(value = "update resource", notes = "update resource info")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "resource ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "resourceForm", value = "resource entity", required = true, dataType = "ResourceForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody ResourceForm resourceForm) {
        Resource resource = resourceForm.toPo(id, Resource.class);
        return Result.success(resourceService.update(resource));
    }

    @ApiOperation(value = "get resource", notes = "get resource")
    @ApiImplicitParam(paramType = "path", name = "id", value = "resource ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id: {}", id);
        return Result.success(resourceService.get(id));
    }

    @ApiOperation(value = "query resource", notes = "query resource by userId")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "user id", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @GetMapping(value = "/user/{username}")
    public Result queryByUsername(@PathVariable String username) {
        log.debug("query with username: {}", username);
        return Result.success(resourceService.query(username));
    }

    @ApiOperation(value = "query all resources", notes = "query all resources info")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @GetMapping(value = "/all")
    public Result queryAll() {
        return Result.success(resourceService.getAll());
    }

    @ApiOperation(value = "search resource", notes = "search resource by conditions")
    @ApiImplicitParam(name = "resourceQueryForm", value = "resource query param", required = true, dataType = "ResourceQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody ResourceQueryForm resourceQueryForm) {
        log.debug("query with name: {}", resourceQueryForm);
        return Result.success(resourceService.query(resourceQueryForm.getPage(), resourceQueryForm.toParam(ResourceQueryParam.class)));
    }
}
