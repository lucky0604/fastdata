package com.fastdata.sysadmin.organization.exception;

import com.fastdata.common.core.exception.BaseException;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 11:58 PM
 * @Version: 1.0
 * @Description:
 **/

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(OrganizationErrorType.USER_NOT_FOUND);
    }

    public UserNotFoundException(String msg) {
        super(OrganizationErrorType.USER_NOT_FOUND, msg);
    }
}
