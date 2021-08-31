package com.fastdata.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Date: 8/31/21 6:11 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("groups")
public class Group extends BasePo {

    private String name;
    private String parentId;
    private String desc;
    @TableLogic
    private String deleted = "N";
}
