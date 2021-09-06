package com.fastdata.sysadmin.organization.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.form.MenuForm;
import com.fastdata.sysadmin.organization.entity.form.MenuQueryForm;
import com.fastdata.sysadmin.organization.entity.param.MenuQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Menu;
import com.fastdata.sysadmin.organization.service.IMenuService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/4/21 12:23 AM
 * @Version: 1.0
 * @Description:
 **/
@RestController
@RequestMapping("/menu")
@Api("menu")
@Slf4j
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "add new menu", notes = "add new menu")
    @ApiImplicitParam(name = "menuForm", value = "add new menu form", required = true, dataType = "MenuForm")
    @PostMapping
    public Result add(@Valid @RequestBody MenuForm menuForm) {
        log.debug("name: {}", menuForm);
        Menu menu = menuForm.toPo(Menu.class);
        return Result.success(menuService.add(menu));
    }

    @ApiOperation(value = "delete menu", notes = "delete menu by id in the url")
    @ApiImplicitParam(paramType = "path", name = "id", value = "menu ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(menuService.delete(id));
    }

    @ApiOperation(value = "update menu", notes = "update menu info")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "menu ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "menuForm", value = "menu entity", required = true, dataType = "MenuForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody MenuForm menuForm) {
        Menu menu = menuForm.toPo(Menu.class);
        menu.setId(id);
        return Result.success(menuService.update(menu));
    }

    @ApiOperation(value = "get menu", notes = "get menu info")
    @ApiImplicitParam(paramType = "path", name = "id", value = "menu ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id: {}", id);
        return Result.success(menuService.get(id));
    }

    @ApiOperation(value = "query menu", notes = "query menu by conditions, simple query")
    @ApiImplicitParam(paramType = "query", name = "name", value = "menu name", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "success", response = Result.class)
    )
    @GetMapping
    public Result query(@RequestParam String name) {
        log.debug("query with name: {}", name);
        MenuQueryParam menuQueryParam = new MenuQueryParam(name);
        return Result.success(menuService.query(menuQueryParam));
    }

    @ApiOperation(value = "search menu", notes = "search menu by conditions")
    @ApiImplicitParam(name = "menuQueryForm", value = "menu query param", required = true, dataType = "MenuQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "success", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody MenuQueryForm menuQueryForm) {
        log.debug("search with menuQueryForm: {}", menuQueryForm);
        return Result.success(menuService.query(menuQueryForm.toParam(MenuQueryParam.class)));
    }

    @ApiOperation(value = "query menu by parent id", notes = "query menu by parent id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "parent ID", required = true, dataType = "string")
    @GetMapping(value = "/parent/{id}")
    public Result search(@PathVariable String id) {
        log.debug("query with parent id: {}", id);
        return Result.success(menuService.queryByParentId(id));
    }
}
