package com.fastdata.sysadmin.organization;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 2021/9/15 - 23:35
 * @Version: 1.0
 * @description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "com.fastdata")
@EnableCreateCacheAnnotation
public class OrganizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }
}
