package com.fastdata.gateway.events;

import com.fastdata.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 2021/11/24 - 15:37
 * @Version: 1.0
 * @Description:
 **/
@Component
@Slf4j
public class BusReceiver {

    @Autowired
    private IRouteService routeService;

    public void handleMessage(RouteDefinition routeDefinition) {
        log.info("Received message: <{}>", routeDefinition);
        routeService.save(routeDefinition);
    }
}
