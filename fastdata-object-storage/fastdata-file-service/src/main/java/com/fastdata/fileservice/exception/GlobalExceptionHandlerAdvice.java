package com.fastdata.fileservice.exception;

import com.fastdata.common.core.entity.vo.Result;
import com.fastdata.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 3:43 PM
 * @Version: 1.0
 * @Description:
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MinioFileException.class})
    public Result uploadFileFailure(MinioFileException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }
}
