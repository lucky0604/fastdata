package com.fastdata.sysadmin.organization.entity.po;

import com.fastdata.common.web.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:17 AM
 * @Version: 1.0
 * @Description:
 **/
@Data
@NoArgsConstructor
public class Resource extends BasePo {
    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;
}
