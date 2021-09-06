package com.fastdata.sysadmin.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastdata.sysadmin.organization.entity.param.GroupQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Group;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:00 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IGroupService extends IService<Group> {
    Group get(String id);

    boolean add(Group group);

    List<Group> query(GroupQueryParam groupQueryParam);

    List<Group> queryByParentId(String id);

    boolean update(Group group);

    boolean delete(String id);
}
