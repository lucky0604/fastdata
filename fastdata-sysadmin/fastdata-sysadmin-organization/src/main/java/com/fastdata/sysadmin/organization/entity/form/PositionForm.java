package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.sysadmin.organization.entity.po.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:43 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class PositionForm extends BaseForm<Position> {

    @NotBlank(message = "position name can't be blank")
    @ApiModelProperty(value = "position name")
    private String name;

    @ApiModelProperty(value = "position desc")
    private String desc;
}
