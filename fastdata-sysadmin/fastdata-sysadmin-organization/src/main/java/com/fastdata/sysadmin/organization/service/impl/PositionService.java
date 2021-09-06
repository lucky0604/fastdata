package com.fastdata.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastdata.sysadmin.organization.dao.PositionMapper;
import com.fastdata.sysadmin.organization.entity.param.PositionQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Position;
import com.fastdata.sysadmin.organization.service.IPositionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:25 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class PositionService extends ServiceImpl<PositionMapper, Position> implements IPositionService {


    @Override
    @Cached(name = "position::", key = "#id", cacheType = CacheType.BOTH)
    public Position get(String id) {
        return this.getById(id);
    }

    @Override
    public boolean add(Position position) {
        return this.save(position);
    }

    @Override
    public List<Position> query(PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = positionQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(positionQueryParam.getName()), "name", positionQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#position.id")
    public boolean update(Position position) {
        return updateById(position);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
