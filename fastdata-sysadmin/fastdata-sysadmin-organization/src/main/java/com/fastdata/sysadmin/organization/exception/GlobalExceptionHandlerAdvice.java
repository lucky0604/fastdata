package com.fastdata.sysadmin.organization.exception;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 11:46 PM
 * @Version: 1.0
 * @Description:
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result userNotFound(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }
}
