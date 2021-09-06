package com.fastdata.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastdata.sysadmin.organization.dao.GroupMapper;
import com.fastdata.sysadmin.organization.entity.param.GroupQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Group;
import com.fastdata.sysadmin.organization.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:09 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class GroupService extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Override
    @Cached(name = "group::", key = "#id", cacheType = CacheType.BOTH)
    public Group get(String id) {
        return this.getById(id);
    }

    @Override
    public boolean add(Group group) {
        return save(group);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> queryWrapper = groupQueryParam.build();
        queryWrapper.eq("name", groupQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Group> queryByParentId(String id) {
        return this.list(new QueryWrapper<Group>().eq("parent_id", id));
    }

    @Override
    @CacheInvalidate(name = "group::", key = "#group.id")
    public boolean update(Group group) {
        return this.updateById(group);
    }

    @Override
    @CacheInvalidate(name = "group::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
