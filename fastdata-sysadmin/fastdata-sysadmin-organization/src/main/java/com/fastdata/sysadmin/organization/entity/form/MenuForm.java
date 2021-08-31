package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.sysadmin.organization.entity.po.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:36 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class MenuForm extends BaseForm<Menu> {

    @NotBlank(message = "menu parent id can't be blank")
    @ApiModelProperty(value = "menu parent id")
    private String parentId;

    @NotBlank(message = "menu name can't be blank")
    @ApiModelProperty(value = "menu name")
    private String name;

    @NotBlank(message = "menu type can't be blank")
    @ApiModelProperty(value = "menu type")
    private String type;

    @NotBlank(message = "menu path can't be blank")
    @ApiModelProperty(value = "menu path")
    private String href;

    @ApiModelProperty(value = "menu icon")
    private String icon;

    @ApiModelProperty(value = "menu order")
    private String orderNum;

    @ApiModelProperty(value = "menu desc")
    private String desc;
}
