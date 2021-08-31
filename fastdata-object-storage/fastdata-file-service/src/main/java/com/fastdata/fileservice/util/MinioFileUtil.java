package com.fastdata.fileservice.util;

import com.fastdata.fileservice.configure.MinioProperties;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:50 PM
 * @Version: 1.0
 * @Description:
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class MinioFileUtil {

    private final MinioProperties minioProperties;
    private final MinioClient client;

    public void createBucket(String bucketName) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        BucketExistsArgs build = BucketExistsArgs.builder().bucket(bucketName).build();
        if (!client.bucketExists(build)) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public String uploadFile(MultipartFile file, String bucketName) throws
            IOException, ServerException,
            InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (file == null || file.getSize() == 0) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assert originalFilename != null;
        String fileName = bucketName + "_" + System.currentTimeMillis() + "_" + format.format(new Date()) + "_" + new Random().nextInt(1000)
                + originalFilename.substring(originalFilename.lastIndexOf("."));

        client.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build());
        return minioProperties.getEndpoint() + "/" + bucketName + "/" + fileName;
    }

    public void removeFile(String bucketName, String objectName) throws
            ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());

    }

    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return client.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
