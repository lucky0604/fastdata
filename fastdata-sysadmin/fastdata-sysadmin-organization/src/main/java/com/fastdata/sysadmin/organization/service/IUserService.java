package com.fastdata.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastdata.sysadmin.organization.entity.param.UserQueryParam;
import com.fastdata.sysadmin.organization.entity.po.User;
import com.fastdata.sysadmin.organization.entity.vo.UserVo;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:39 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IUserService {

    UserVo get(String id);

    User getByUniqueId(String uniqueId);

    boolean add(User user);

    IPage<UserVo> query(Page<User> page, UserQueryParam userQueryParam);

    boolean update(User user);

    boolean delete(String id);
}
