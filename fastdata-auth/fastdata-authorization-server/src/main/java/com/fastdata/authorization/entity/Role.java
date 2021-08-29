package com.fastdata.authorization.entity;

import com.fastdata.common.web.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 11:00 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Role extends BasePo {

    private String code;
    private String name;
    private String desc;
}
