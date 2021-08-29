package com.fastdata.auth.events;

import com.fastdata.auth.service.impl.ResourceService;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:19 AM
 * @Version: 1.0
 * @Description:
 **/

@Component
@Slf4j
public class BusReceiver {

    @Autowired
    private ResourceService resourceService;

    public void handleMsg(Resource resource) {
        log.info("Received message: <{}>", resource);
        resourceService.saveResource(resource);
    }
}
