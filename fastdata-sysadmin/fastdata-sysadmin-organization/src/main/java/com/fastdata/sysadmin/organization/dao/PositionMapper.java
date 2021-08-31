package com.fastdata.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastdata.sysadmin.organization.entity.po.Position;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 7:17 PM
 * @Version: 1.0
 * @Description:
 **/
@Repository
@Mapper
public interface PositionMapper extends BaseMapper<Position> {
}
