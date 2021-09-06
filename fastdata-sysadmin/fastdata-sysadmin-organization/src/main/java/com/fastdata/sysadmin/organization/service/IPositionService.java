package com.fastdata.sysadmin.organization.service;

import com.fastdata.sysadmin.organization.entity.param.PositionQueryParam;
import com.fastdata.sysadmin.organization.entity.po.Position;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 9/1/21 12:23 AM
 * @Version: 1.0
 * @Description:
 **/

public interface IPositionService {

    Position get(String id);

    boolean add(Position position);

    List<Position> query(PositionQueryParam positionQueryParam);

    boolean update(Position position);

    boolean delete(String id);
}
