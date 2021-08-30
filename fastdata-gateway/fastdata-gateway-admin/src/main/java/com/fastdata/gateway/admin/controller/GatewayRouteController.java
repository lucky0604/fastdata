package com.fastdata.gateway.admin.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.gateway.admin.entity.form.GatewayRouteForm;
import com.fastdata.gateway.admin.entity.form.GatewayRouteQueryForm;
import com.fastdata.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.fastdata.gateway.admin.entity.po.GatewayRoute;
import com.fastdata.gateway.admin.entity.vo.GatewayRouteVo;
import com.fastdata.gateway.admin.service.IGatewayRouteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 5:20 PM
 * @Version: 1.0
 * @Description:
 **/
@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
@Slf4j
public class GatewayRouteController {

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @ApiOperation(value = "create gateway route", notes = "add a new gateway route")
    @ApiImplicitParam(name = "gatewayRouteForm", value = "gateway route form", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public Result add(@Valid @RequestBody GatewayRouteForm gatewayRouteForm) {
        log.info("name:", gatewayRouteForm);
        GatewayRoute gatewayRoute = gatewayRouteForm.toPo(GatewayRoute.class);
        return Result.success(gatewayRouteService.add(gatewayRoute));
    }

    @ApiOperation(value = "delete gateway route", notes = "delete the route object based on url id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "gateway route id", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(gatewayRouteService.delete(id));
    }

    @ApiOperation(value = "edit gateway route", notes = "edit the specified gateway route")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "gateway route id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "gatewayRouteForm", value = "gateway route form", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody GatewayRouteForm gatewayRouteForm) {
        GatewayRoute gatewayRoute = gatewayRouteForm.toPo(GatewayRoute.class);
        gatewayRoute.setId(id);
        return Result.success(gatewayRouteService.update(gatewayRoute));
    }

    @ApiOperation(value = "get gateway route", notes = "get gateway route by id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Gateway route ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.info("get with id: {}", id);
        return Result.success(new GatewayRouteVo(gatewayRouteService.get(id)));
    }

    @ApiOperation(value = "get gateway by uri", notes = "get gateway by uri")
    @ApiImplicitParam(paramType = "query", name = "name", value = "gateway route path", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "handle success", response = Result.class)
    )
    public Result getByUri(@RequestParam String uri) {
        return Result.success(gatewayRouteService.query(new GatewayRouteQueryParam(uri)).stream().findFirst());
    }

    @ApiOperation(value = "search gateway route", notes = "search gateway route")
    @ApiImplicitParam(name = "gatewayRouteQueryForm", value = "gateway query params", required = true, dataType = "GatewayRouteQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "success", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm) {
        return Result.success(gatewayRouteService.query(gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class)));
    }

    @ApiOperation(value = "overload the gateway route", notes = "reload the gateway route into redis")
    @ApiResponses(
            @ApiResponse(code = 200, message = "success", response = Result.class)
    )
    @PostMapping(value = "/overload")
    public Result overload() {
        return Result.success(gatewayRouteService.overload());
    }

}
