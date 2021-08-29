package com.fastdata.common.web.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fastdata.common.web.entity.po.BasePo;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:05 AM
 * @Version: 1.0
 * @Description:
 **/

@Data
public class BaseParam<T extends BasePo> {

    private Date createdTimeStart;
    private Date createdTimeEnd;

    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.ge(null != this.createdTimeStart, "created_time", this.createdTimeStart)
                .le(null != this.createdTimeEnd, "created_time", this.createdTimeEnd);
        return queryWrapper;
    }
}
