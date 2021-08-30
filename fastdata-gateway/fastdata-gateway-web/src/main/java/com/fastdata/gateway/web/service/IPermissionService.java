package com.fastdata.gateway.web.service;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 11:03 PM
 * @Version: 1.0
 * @Description:
 **/

public interface IPermissionService {

    boolean permission(String authentication, String url, String method);
}
