package com.fastdata.authorization.service.impl;

import com.fastdata.authorization.entity.User;
import com.fastdata.authorization.provider.OrganizationProvider;
import com.fastdata.authorization.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 12:33 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
