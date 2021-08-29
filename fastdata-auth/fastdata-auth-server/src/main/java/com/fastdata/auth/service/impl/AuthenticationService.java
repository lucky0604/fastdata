package com.fastdata.auth.service.impl;

import com.fastdata.auth.service.IAuthenticationService;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 1:57 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {

    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private ResourceService resourceService;

    @Override
    public boolean decide(HttpServletRequest request) {
        log.debug("visiting url: {}, method: {}", request.getServletPath(), request.getMethod());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ConfigAttribute urlConfigAttribute = resourceService.findConfigAttributesByUrl(request);
        if (NONEXISTENT_URL.equals(urlConfigAttribute.getAttribute())) {
            log.debug("url not found in the resource, access denied");
        }
        Set<Resource> userResources = findResourcesByUsername(authentication.getName());
        return isMatch(urlConfigAttribute, userResources);
    }

    public boolean isMatch(ConfigAttribute urlConfigAttribute, Set<Resource> userResources) {
        return userResources.stream().anyMatch(resource -> resource.getCode().equals(urlConfigAttribute.getAttribute()));
    }

    private Set<Resource> findResourcesByUsername(String username) {
        Set<Resource> resources = resourceService.queryByUsername(username);
        if (log.isDebugEnabled()) {
            log.debug("user resource size is : {}, info: {}", resources.size(), resources);
        }
        return resources;
    }
}
