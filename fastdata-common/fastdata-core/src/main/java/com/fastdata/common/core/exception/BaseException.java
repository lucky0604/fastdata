package com.fastdata.common.core.exception;

import lombok.Getter;


/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/28/21 10:40 PM
 * @Version: 1.0
 * @Description:
 **/

@Getter
public class BaseException extends RuntimeException {

    // the error type of the exception
    private final ErrorType errorType;

    public BaseException() {
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String msg) {
        super(msg);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String msg, Throwable cause) {
        super(msg, cause);
        this.errorType = errorType;
    }
}
