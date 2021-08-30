package com.fastdata.gateway.web.events;

import com.fastdata.gateway.web.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 11:35 PM
 * @Version: 1.0
 * @Description:
 **/
@Component
@Slf4j
public class BusReceiver {

    @Autowired
    private IRouteService routeService;

    public void handleMessage(RouteDefinition routeDefinition) {
        log.info("Receive Message: <{}>", routeDefinition);
        routeService.save(routeDefinition);
    }
}
