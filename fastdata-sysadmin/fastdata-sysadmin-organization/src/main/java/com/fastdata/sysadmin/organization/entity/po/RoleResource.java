package com.fastdata.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fastdata.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:25 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_resorce_relation")
public class RoleResource extends BasePo {

    private String roleId;
    private String resourceId;
}
