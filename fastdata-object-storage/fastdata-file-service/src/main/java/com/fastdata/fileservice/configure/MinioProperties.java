package com.fastdata.fileservice.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:40 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@Component("minioProperties")
@ConfigurationProperties(prefix = "spring.minio")
public class MinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;
}
