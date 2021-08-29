package com.fastdata.common.core.exception;

import lombok.Getter;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/28/21 10:43 PM
 * @Version: 1.0
 * @Description:
 **/

@Getter
public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR("-1", "system exception"),
    SYSTEM_BUSY("000001", "system is busy, please try again later"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "can't find the service"),
    GATEWAY_ERROR("010500", "GATEWAY exception"),
    GATEWAY_CONNECT_TIME_OUT("010002", "GATEWAY connect timeout"),

    ARGUMENT_NOT_VALID("020000", "request param is not valid"),
    INVALID_TOKEN("020001", "invalid token"),
    UPLOAD_FILE_SIZE_LIMIT("020010", "upload file is oversized"),

    DUPLICATE_PRIMARY_KEY("030000", "primary key conflict");

    private String code;
    private String msg;

    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
