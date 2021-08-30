package com.fastdata.gateway.admin.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastdata.gateway.admin.config.BusConfig;
import com.fastdata.gateway.admin.dao.GatewayRouteMapper;
import com.fastdata.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.fastdata.gateway.admin.entity.po.FilterDefinition;
import com.fastdata.gateway.admin.entity.po.GatewayRoute;
import com.fastdata.gateway.admin.entity.po.PredicateDefinition;
import com.fastdata.gateway.admin.entity.vo.GatewayRouteVo;
import com.fastdata.gateway.admin.events.EventSender;
import com.fastdata.gateway.admin.service.IGatewayRouteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 4:44 PM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class GatewayRouteService extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Autowired
    private EventSender eventSender;

    @Override
    public boolean add(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.save(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        eventSender.send(BusConfig.ROUTING_KEY, routeDefinition);
        return isSuccess;
    }

    @Override
    public boolean delete(String id) {
        GatewayRoute gatewayRoute = this.getById(id);
        gatewayRouteCache.remove(gatewayRoute.getRouteId());
        eventSender.send(BusConfig.ROUTING_KEY, gatewayRouteToRouteDefinition(gatewayRoute));
        return this.removeById(id);
    }

    @Override
    public boolean update(GatewayRoute gatewayRoute) {
        boolean isSuccess = this.updateById(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        eventSender.send(BusConfig.ROUTING_KEY, routeDefinition);
        return isSuccess;
    }

    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
        } catch (IOException e) {
            log.error("Gateway route object translate failed", e);
        }
        return routeDefinition;
    }

    @Override
    public GatewayRoute get(String id) {
        return this.getById(id);
    }

    @Override
    public List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        QueryWrapper<GatewayRoute> queryWrapper = gatewayRouteQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQueryParam.getUri()), "uri", gatewayRouteQueryParam.getUri());
        return this.list(queryWrapper).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
    }

    @Override
    @PostConstruct
    public boolean overload(){
        List<GatewayRoute> gatewayRoutes = this.list(new QueryWrapper<>());
        gatewayRoutes.forEach(gatewayRoute -> gatewayRouteCache.put(gatewayRoute.getRouteId(), gatewayRouteToRouteDefinition(gatewayRoute)));
        log.info("global init gateway route successful");
        return true;
    }
}
