package com.fastdata.sysadmin.organization.exception;

import com.fastdata.common.core.exception.ErrorType;
import lombok.Getter;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 11:56 PM
 * @Version: 1.0
 * @Description:
 **/
@Getter
public enum OrganizationErrorType implements ErrorType {

    USER_NOT_FOUND("030100", "user not found!"),
    ROLE_NOT_FOUND("030200", "role not found!");

    private String code;
    private String msg;

    OrganizationErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
