package com.fastdata.sysadmin.organization.entity.param;

import com.fastdata.common.web.entity.param.BaseParam;
import com.fastdata.sysadmin.organization.entity.po.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:29 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupQueryParam extends BaseParam<Group> {
    private String name;
}
