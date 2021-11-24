package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.sysadmin.organization.entity.po.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:50 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class ResourceForm extends BaseForm<Resource> {

    @NotBlank(message = "resource name can't be blank")
    @ApiModelProperty(value = "resource name")
    private String name;

    @NotBlank(message = "resource code can't be blank")
    @ApiModelProperty(value = "resource code")
    private String code;

    @NotBlank(message = "resource type can't be blank")
    @ApiModelProperty(value = "resource type")
    private String type;

    @NotBlank(message = "resource path can't be blank")
    @ApiModelProperty(value = "resource url")
    private String url;

    @NotBlank(message = "resource method can't be blank")
    @ApiModelProperty(value = "resource method")
    private String method;

    @ApiModelProperty(value = "resource desc")
    private String description;
}
