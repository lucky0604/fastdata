package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.sysadmin.organization.entity.po.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 7:00 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class RoleForm extends BaseForm<Role> {

    @NotBlank(message = "role code can't be blank")
    @ApiModelProperty(value = "role code")
    private String code;

    @NotBlank(message = "role name can't be blank")
    @ApiModelProperty(value = "role name")
    private String name;

    @ApiModelProperty(value = "role desc")
    private String desc;

    @ApiModelProperty(value = "role resources")
    private Set<String> resourceIds;
}
