package com.fastdata.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastdata.sysadmin.organization.dao.RoleMapper;
import com.fastdata.sysadmin.organization.entity.param.RoleQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Role;
import com.fastdata.sysadmin.organization.exception.RoleNotFoundException;
import com.fastdata.sysadmin.organization.service.IRoleResourceService;
import com.fastdata.sysadmin.organization.service.IRoleService;
import com.fastdata.sysadmin.organization.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 1:02 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleResourceService roleResourceService;

    @Override
    @Cached(name = "role::", key = "#id", cacheType = CacheType.BOTH)
    public Role get(String id) {
        Role role = this.getById(id);
        if (Objects.isNull(role)) {
            throw new RoleNotFoundException("role not found with id: " + id);
        }
        role.setResourceIds(roleResourceService.queryByRoleId(id));
        return role;
    }

    @Override
    public List<Role> getAll() {
        return this.list();
    }

    @Override
    public boolean add(Role role) {
        boolean isSuccess = this.save(role);
        roleResourceService.saveBatch(role.getId(), role.getResourceIds());
        return isSuccess;
    }

    @Override
    public IPage<Role> query(Page page, RoleQueryParam roleQueryParam) {
        QueryWrapper<Role> queryWrapper = roleQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getName()), "name", roleQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getCode()), "code", roleQueryParam.getCode());
        return this.page(page, queryWrapper);
    }

    @Override
    @Cached(name = "role4user::", key = "#userId", cacheType = CacheType.BOTH)
    public List<Role> query(String userId) {
        Set<String> roleIds = userRoleService.queryByUserId(userId);
        return (List<Role>) this.listByIds(roleIds);
    }

    @Override
    @CacheInvalidate(name = "role::", key = "#role.id")
    public boolean update(Role role) {
        boolean isSuccess = this.updateById(role);
        roleResourceService.saveBatch(role.getId(), role.getResourceIds());
        return isSuccess;
    }

    @Override
    @CacheInvalidate(name = "role::", key = "#id")
    public boolean delete(String id) {
        roleResourceService.removeByRoleId(id);
        return this.removeById(id);
    }
}
