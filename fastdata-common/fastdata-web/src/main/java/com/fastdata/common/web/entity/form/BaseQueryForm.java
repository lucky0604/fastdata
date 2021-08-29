package com.fastdata.common.web.entity.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastdata.common.web.entity.param.BaseParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:12 AM
 * @Version: 1.0
 * @Description:
 **/

@ApiModel
@Slf4j
@Data
public class BaseQueryForm<P extends BaseParam> extends BaseForm {

    private long current = 1;

    private long size = 10;

    public P toParam(Class<P> clazz) {
        P p = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, p);
        return p;
    }

    public Page getPage() {
        return new Page(this.getCurrent(), this.getSize());
    }
}
