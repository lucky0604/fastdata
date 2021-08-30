package com.fastdata.gateway.admin.service;

import com.fastdata.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.fastdata.gateway.admin.entity.po.GatewayRoute;
import com.fastdata.gateway.admin.entity.vo.GatewayRouteVo;

import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 4:42 PM
 * @Version: 1.0
 * @Description:
 **/

public interface IGatewayRouteService {

    GatewayRoute get(String id);

    boolean add(GatewayRoute gatewayRoute);

    List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    boolean update(GatewayRoute gatewayRoute);

    boolean delete(String id);

    boolean overload();
}
