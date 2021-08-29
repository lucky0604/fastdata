package com.fastdata.auth.config;

import com.fastdata.auth.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 1:41 AM
 * @Version: 1.0
 * @Description:
 **/
@Component
class LoadResourceDefine {

    @Autowired
    private IResourceService resourceService;

    @PostConstruct
    public void resourceConfigAttributes() {
        resourceService.loadResource();
    }
}
