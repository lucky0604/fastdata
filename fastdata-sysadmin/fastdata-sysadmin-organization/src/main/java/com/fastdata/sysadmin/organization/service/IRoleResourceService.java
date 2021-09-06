package com.fastdata.sysadmin.organization.service;

import com.fastdata.sysadmin.organization.entity.po.RoleResource;

import java.util.List;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:36 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IRoleResourceService {

    boolean saveBatch(String roleId, Set<String> resourceIds);

    boolean removeByRoleId(String roleId);

    Set<String> queryByRoleId(String roleId);

    List<RoleResource> queryByRoleIds(Set<String> roleids);
}
