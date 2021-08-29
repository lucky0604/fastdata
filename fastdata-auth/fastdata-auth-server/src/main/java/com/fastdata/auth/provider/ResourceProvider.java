package com.fastdata.auth.provider;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:29 AM
 * @Version: 1.0
 * @Description:
 **/

@FeignClient(name = "organization", fallback = ResourceProviderFallback.class)
public interface ResourceProvider {
    @GetMapping(value = "/resource/all")
    Result<Set<Resource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    Result<Set<Resource>> resources(@PathVariable("username") String username);
}
