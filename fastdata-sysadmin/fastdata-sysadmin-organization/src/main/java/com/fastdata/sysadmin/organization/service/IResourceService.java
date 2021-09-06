package com.fastdata.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastdata.sysadmin.organization.entity.param.ResourceQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Resource;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:32 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IResourceService {

    Resource get(String id);

    boolean add(Resource resource);

    IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam);

    List<Resource> getAll();

    List<Resource> query(String username);

    boolean update(Resource resource);

    boolean delete(String id);
}
