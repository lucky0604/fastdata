package com.fastdata.authorization.service;

import com.fastdata.authorization.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 11:08 PM
 * @Version: 1.0
 * @Description:
 **/
@Service
public interface IRoleService {

    Set<Role> queryUserRolesByUserId(String userId);
}
