package com.fastdata.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastdata.sysadmin.organization.dao.UserMapper;
import com.fastdata.sysadmin.organization.entity.param.UserQueryParam;
import com.fastdata.sysadmin.organization.entity.po.User;
import com.fastdata.sysadmin.organization.entity.vo.UserVo;
import com.fastdata.sysadmin.organization.exception.UserNotFoundException;
import com.fastdata.sysadmin.organization.service.IUserRoleService;
import com.fastdata.sysadmin.organization.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/3/21 11:38 PM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Cached(name = "user::", key = "#id", cacheType = CacheType.BOTH)
    public UserVo get(String id) {
        User user = this.getById(id);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("user not found with id: " + id);
        }
        user.setRoleIds(userRoleService.queryByUserId(id));
        return new UserVo(user);
    }

    @Override
    @Cached(name = "user::", key = "#uniqueId", cacheType = CacheType.BOTH)
    public User getByUniqueId(String uniqueId) {
        User user = this.getOne(new QueryWrapper<User>()
                .eq("username", uniqueId)
                .or()
                .eq("mobile", uniqueId));
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("user not found with uniqueid: " + uniqueId);
        }
        user.setRoleIds(userRoleService.queryByUserId(user.getId()));
        return user;
    }

    @Override
    @Transactional
    public boolean add(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        boolean inserts = this.save(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return inserts;
    }

    @Override
    public IPage<UserVo> query(Page<User> page, UserQueryParam userQueryParam) {
        QueryWrapper<User> queryWrapper = userQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getName()), "name", userQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getUsername()), "username", userQueryParam.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getMobile()), "mobile", userQueryParam.getMobile());

        IPage<User> iPageUser = this.page(page, queryWrapper);
        return iPageUser.convert(UserVo::new);
    }

    @Override
    @Transactional
    @CacheInvalidate(name = "user::", key = "#user.id")
    public boolean update(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        boolean isSuccess = this.updateById(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return isSuccess;
    }

    @Override
    @Transactional
    @CacheInvalidate(name = "user::", key = "#id")
    public boolean delete(String id) {
        this.removeById(id);
        return userRoleService.removeByUserId(id);
    }
}
