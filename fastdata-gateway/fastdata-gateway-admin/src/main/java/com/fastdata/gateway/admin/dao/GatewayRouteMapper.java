package com.fastdata.gateway.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastdata.gateway.admin.entity.po.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 4:37 PM
 * @Version: 1.0
 * @Description:
 **/
@Mapper
@Repository
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}
