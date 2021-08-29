package com.fastdata.auth.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.fastdata.auth.provider.ResourceProvider;
import com.fastdata.auth.service.IResourceService;
import com.fastdata.auth.service.NewMvcRequestMatcher;
import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:26 AM
 * @Version: 1.0
 * @Description:
 **/

@Service
@Slf4j
public class ResourceService implements IResourceService {

    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Autowired
    private ResourceProvider resourceProvider;

    private static final Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes = new HashMap<>();

    @Override
    public synchronized void saveResource(Resource resource) {
        resourceConfigAttributes.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode())
        );
        log.info("resourceConfigAttributes size: {}", resourceConfigAttributes.size());
    }

    @Override
    public synchronized void removeResource(Resource resource) {
        resourceConfigAttributes.remove(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod())
        );
        log.info("resourceConfigAttributes size: {}", resourceConfigAttributes.size());
    }

    @Override
    public synchronized void loadResource() {
        Result<Set<Resource>> resourcesResult = resourceProvider.resources();
        if (resourcesResult.isFail()) {
            System.exit(1);
        }
        Set<Resource> resources = resourcesResult.getData();
        Map<MvcRequestMatcher, SecurityConfig> tempResourceConfigAttributes = resources.stream().collect(Collectors.toMap(
                resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                resource -> new SecurityConfig(resource.getCode())
        ));
        resourceConfigAttributes.putAll(tempResourceConfigAttributes);
        log.debug("init resourceConfigAttributes: {}", resourceConfigAttributes);
    }

    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> log.debug("url in the resource config: {}", urlConfigAttribute.getAttribute()))
                .findFirst().orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    @Override
    @Cached(name = "resource4user::", key = "#username", cacheType = CacheType.LOCAL)
    public Set<Resource> queryByUsername(String username) {
        return resourceProvider.resources(username).getData();
    }

    private MvcRequestMatcher newMvcRequestMatcher(String url, String method) {
        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, method);
    }
}
