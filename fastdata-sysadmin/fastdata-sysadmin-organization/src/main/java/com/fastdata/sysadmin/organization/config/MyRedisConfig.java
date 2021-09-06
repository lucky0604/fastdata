package com.fastdata.sysadmin.organization.config;

import com.fastdata.common.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 11:03 PM
 * @Version: 1.0
 * @Description:
 **/
@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}
