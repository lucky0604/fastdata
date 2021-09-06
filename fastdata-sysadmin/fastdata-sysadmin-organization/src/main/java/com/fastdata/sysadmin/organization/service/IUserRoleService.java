package com.fastdata.sysadmin.organization.service;

import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 1:03 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IUserRoleService {

    boolean saveBatch(String userId, Set<String> roleIds);

    boolean removeByUserId(String userId);

    Set<String> queryByUserId(String userId);
}
