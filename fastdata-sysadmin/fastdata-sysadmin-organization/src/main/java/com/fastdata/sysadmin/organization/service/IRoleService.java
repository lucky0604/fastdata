package com.fastdata.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastdata.sysadmin.organization.entity.param.RoleQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Role;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:38 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IRoleService {

    Role get(String id);

    List<Role> getAll();

    boolean add(Role role);

    IPage<Role> query(Page page, RoleQueryParam roleQueryParam);

    List<Role> query(String userId);

    boolean update(Role role);

    boolean delete(String id);
}
