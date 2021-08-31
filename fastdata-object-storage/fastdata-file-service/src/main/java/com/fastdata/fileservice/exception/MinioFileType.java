package com.fastdata.fileservice.exception;

import com.fastdata.common.core.exception.ErrorType;
import lombok.Getter;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:45 PM
 * @Version: 1.0
 * @Description:
 **/
@Getter
public enum MinioFileType implements ErrorType {

    FILE_UPLOAD_FAILED("040100", "file upload failed"),
    FILE_REMOVE_FAILED("040200", "file remove failed"),
    FILE_GET_FAILED("040300", "get file failed"),
    FAILED_DOWNLOAD_FILE("040400", "download file failed");

    private String code;
    private String msg;

    MinioFileType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
