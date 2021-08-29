package com.fastdata.auth.provider;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:31 AM
 * @Version: 1.0
 * @Description:
 **/

@Component
@Slf4j
public class ResourceProviderFallback implements ResourceProvider {

    @Override
    public Result<Set<Resource>> resources() {
        log.error("can't load the resources");
        return Result.fail();
    }

    @Override
    public Result<Set<Resource>> resources(String username) {
        log.error("can't load the user resources");
        return Result.success(new HashSet<Resource>());
    }
}
