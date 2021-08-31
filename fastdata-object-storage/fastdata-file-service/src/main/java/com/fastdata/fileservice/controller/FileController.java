package com.fastdata.fileservice.controller;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.fileservice.entity.FileObject;
import com.fastdata.fileservice.exception.MinioFileException;
import com.fastdata.fileservice.exception.MinioFileType;
import com.fastdata.fileservice.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 4:08 PM
 * @Version: 1.0
 * @Description:
 **/
@Api(tags = "File management")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private final IFileService fileService;

    public FileController(@Qualifier("minioServiceImpl") IFileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "upload file", notes = "upload file", response = Result.class)
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam(value = "file")MultipartFile file, @RequestParam(value = "bucketName") String bucketName) {
        return Result.success(fileService.uploadFile(file, bucketName));
    }

    @ApiOperation(value = "delete file", notes = "delete file", response = Result.class)
    @DeleteMapping("/remove")
    public Result<?> remove(@Valid @RequestBody FileObject fileObject) {
        fileService.removeFile(fileObject.getBucketName(), fileObject.getFileName());
        return Result.success();
    }

    @ApiOperation(value = "image preview", notes = "image preview")
    @GetMapping("/previewPicture/{fileName}")
    public void previewPicture(@PathVariable("fileName") String objectName,
                               @RequestParam(value = "bucketname") String bucketName, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            InputStream stream = fileService.getObject(bucketName, objectName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while ( -1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();
            out.write(bytes);
            out.flush();
        }
    }

    @ApiOperation(value = "download file to local", notes = "download file to local")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable("fileName") String objectName,
                                           @RequestParam(value = "bucketName") String bucketName) throws Exception {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream stream = null;
        ByteArrayOutputStream output = null;
        try {
            stream = fileService.getObject(bucketName, objectName);
            if (stream == null) {
                throw new MinioFileException(MinioFileType.FAILED_DOWNLOAD_FILE, "");
            }

            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept-Ranges", "bytes");
            httpHeaders.add("Content-Length", bytes.length + "");
            objectName = new String(objectName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            httpHeaders.add("Content-disposition", "attachment;filename=" + objectName);
            httpHeaders.add("Content-Type", "text/plain;charset=utf-8");
            responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (output != null) {
                output.close();
            }
        }
        return responseEntity;
    }
}
