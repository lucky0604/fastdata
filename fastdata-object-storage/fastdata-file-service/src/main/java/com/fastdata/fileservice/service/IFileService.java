package com.fastdata.fileservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 4:01 PM
 * @Version: 1.0
 * @Description:
 **/

public interface IFileService {

    String uploadFile(MultipartFile file, String bucketName);

    void removeFile(String bucketName, String objectName);

    InputStream getObject(String bucketName, String objectName);
}
