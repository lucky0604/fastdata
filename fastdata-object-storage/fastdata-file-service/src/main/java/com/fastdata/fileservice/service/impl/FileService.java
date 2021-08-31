package com.fastdata.fileservice.service.impl;

import com.fastdata.fileservice.exception.MinioFileException;
import com.fastdata.fileservice.exception.MinioFileType;
import com.fastdata.fileservice.service.IFileService;
import com.fastdata.fileservice.util.MinioFileUtil;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 4:02 PM
 * @Version: 1.0
 * @Description:
 **/
@Slf4j
@Service("minioServiceImpl")
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final MinioFileUtil minioFileUtil;


    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        try {
            return this.minioFileUtil.uploadFile(file, bucketName);
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException
        | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            log.error("file upload failed: " + e.getMessage(), e);
            throw new MinioFileException();
        }
    }

    @Override
    public void removeFile(String bucketName, String objectName) {
        try {
            this.minioFileUtil.removeFile(bucketName, objectName);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException
        | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            log.error("file delete failed: " + e.getMessage(), e);
            throw new MinioFileException(MinioFileType.FILE_REMOVE_FAILED, "file delete failed");
        }
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        try {
            return this.minioFileUtil.getObject(bucketName, objectName);
        } catch (Exception e) {
            log.error("get file failed" + e.getMessage(), e);
            throw new MinioFileException(MinioFileType.FILE_GET_FAILED, e.getMessage());
        }
    }
}
