package com.fastdata.sysadmin.organization.service;

import com.fastdata.sysadmin.organization.entity.param.MenuQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Menu;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:07 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IMenuService {

    Menu get(String id);

    boolean add(Menu menu);

    List<Menu> query(MenuQueryParam menuQueryParam);

    List<Menu> queryByParentId(String id);

    boolean update(Menu menu);

    boolean delete(String id);
}
