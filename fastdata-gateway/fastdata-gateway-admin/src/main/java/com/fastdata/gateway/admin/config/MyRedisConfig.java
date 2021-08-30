package com.fastdata.gateway.admin.config;

import com.fastdata.common.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 4:26 PM
 * @Version: 1.0
 * @Description:
 **/
@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}
