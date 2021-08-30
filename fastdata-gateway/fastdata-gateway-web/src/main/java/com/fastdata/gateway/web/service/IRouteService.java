package com.fastdata.gateway.web.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 11:01 PM
 * @Version: 1.0
 * @Description:
 **/

public interface IRouteService {
    Collection<RouteDefinition> getRouteDefinitions();
    boolean save(RouteDefinition routeDefinition);
    boolean delete(String routeId);
}
