package com.fastdata.authorization.service.impl;

import com.fastdata.authorization.entity.Role;
import com.fastdata.authorization.provider.OrganizationProvider;
import com.fastdata.authorization.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 12:32 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
public class RoleService implements IRoleService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }
}
