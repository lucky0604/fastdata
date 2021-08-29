package com.fastdata.auth.service;

import com.fastdata.sysadmin.organization.entity.po.Resource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:20 AM
 * @Version: 1.0
 * @Description:
 **/

@Service
public interface IResourceService {

    /**
     * add and update auth dynamic
     * @param resource
     */
    void saveResource(Resource resource);

    /**
     * remove auth dynamic
     * @param resource
     */
    void removeResource(Resource resource);

    void loadResource();

    ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest);

    Set<Resource> queryByUsername(String username);
}
