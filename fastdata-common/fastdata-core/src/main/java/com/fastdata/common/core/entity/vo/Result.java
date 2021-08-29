package com.fastdata.common.core.entity.vo;

import com.fastdata.common.core.exception.BaseException;
import com.fastdata.common.core.exception.ErrorType;
import com.fastdata.common.core.exception.SystemErrorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/28/21 10:50 PM
 * @Version: 1.0
 * @Description:
 **/

@ApiModel(description = "request return result msg")
@Getter
public class Result<T> {

    public static final String SUCCESS_CODE = "000000";
    public static final String SUCCESS_MSG = "success";

    @ApiModelProperty(value = "result code", required = true)
    private String code;
    @ApiModelProperty(value = "result desc")
    private String msg;
    @ApiModelProperty(value = "result timestamp")
    private Instant time;
    @ApiModelProperty(value = "result data info")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }

    public static Result fail(BaseException baseException, Object data) {
        return new Result(baseException.getErrorType(), data);
    }

    public static Result fail(ErrorType errorType, Object data) {
        return new Result(errorType, data);
    }

    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }

    public static Result fail(Object data) {
        return new Result(SystemErrorType.SYSTEM_ERROR, data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
