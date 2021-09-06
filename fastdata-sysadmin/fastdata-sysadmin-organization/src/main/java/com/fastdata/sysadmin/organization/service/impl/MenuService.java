package com.fastdata.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastdata.sysadmin.organization.dao.MenuMapper;
import com.fastdata.sysadmin.organization.entity.param.MenuQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Menu;
import com.fastdata.sysadmin.organization.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:15 AM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
    @Cached(name = "menu::", key = "#id", cacheType = CacheType.BOTH)
    public Menu get(String id) {
        return this.getById(id);
    }

    @Override
    public boolean add(Menu menu) {
        return this.save(menu);
    }

    @Override
    public List<Menu> query(MenuQueryParam menuQueryParam) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != menuQueryParam.getName(), "name", menuQueryParam.getName());
        return this.list(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(String id) {
        return this.list(new QueryWrapper<Menu>().eq("parent_id", id));
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#menu.id")
    public boolean update(Menu menu) {
        return this.updateById(menu);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }
}
