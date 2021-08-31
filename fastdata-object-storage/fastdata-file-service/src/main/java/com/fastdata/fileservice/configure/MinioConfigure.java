package com.fastdata.fileservice.configure;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:40 PM
 * @Version: 1.0
 * @Description:
 **/
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfigure {

    private final MinioProperties properties;

    public MinioConfigure(@Qualifier("minioProperties") MinioProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }
}
