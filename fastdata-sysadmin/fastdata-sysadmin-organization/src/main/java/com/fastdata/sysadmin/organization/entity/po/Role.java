package com.fastdata.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fastdata.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:10 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("roles")
public class Role extends BasePo {

    private String code;
    private String name;
    private String desc;

    @TableField(exist = false)
    private Set<String> resourceIds;
}
