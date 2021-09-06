package com.fastdata.sysadmin.organization.exception;

import com.fastdata.common.core.exception.BaseException;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 11:57 PM
 * @Version: 1.0
 * @Description:
 **/

public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException() {
        super(OrganizationErrorType.ROLE_NOT_FOUND);
    }

    public RoleNotFoundException(String msg) {
        super(OrganizationErrorType.ROLE_NOT_FOUND, msg);
    }
}
