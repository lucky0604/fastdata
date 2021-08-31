package com.fastdata.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Date: 8/31/21 5:40 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User extends BasePo {
    private String name;
    private String mobile;
    private String username;
    private String password;
    private String desc;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    @TableField(exist = false)
    private Set<String> roleIds;
    @TableLogic
    private String deleted = "N";
}
