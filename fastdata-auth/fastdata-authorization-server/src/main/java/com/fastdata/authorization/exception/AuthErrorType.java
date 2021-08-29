package com.fastdata.authorization.exception;

import com.fastdata.common.core.exception.ErrorType;
import lombok.Getter;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 12:39 AM
 * @Version: 1.0
 * @Description:
 **/
@Getter
public enum AuthErrorType implements ErrorType {

    INVALID_REQUEST("040001", "invalid request"),
    INVALID_CLIENT("040002", "invalid client_id"),
    INVALID_GRANT("040003", "invalid authorization"),
    INVALID_SCOPE("040004", "invalid scope"),
    INVALID_TOKEN("040005", "invalid token"),
    INSUFFICIENT_SCOPE("040010", "insufficient scope"),
    REDIRECT_URI_MISMATCH("040020", "redirect url not match"),
    ACCESS_DENIED("040030", "access denied"),
    METHOD_NOT_ALLOWED("040040", "method not allowed"),
    SERVER_ERROR("040050", "authorization server error"),
    UNAUTHORIZED_CLIENT("040060", "unauthorized client"),
    UNAUTHORIZED("040061", "unauthorized"),
    UNSUPPORTED_RESPONSE_TYPE("040070", "unsupported response type"),
    UNSUPPORTED_GRANT_TYPE("040071", "unsupported grant type");

    private String code;
    private String msg;
    AuthErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
