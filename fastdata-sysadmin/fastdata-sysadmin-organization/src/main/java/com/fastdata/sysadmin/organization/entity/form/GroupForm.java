package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.sysadmin.organization.entity.po.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:27 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class GroupForm extends BaseForm<Group> {

    @NotBlank(message = "group parent id can't be blank")
    @ApiModelProperty(value = "group parent id")
    private String parentId;

    @NotBlank(message = "group parent name can't be blank")
    @ApiModelProperty(value = "group name")
    private String name;

    @ApiModelProperty(value = "group desc")
    private String desc;
}
