package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseQueryForm;
import com.fastdata.sysadmin.organization.entity.param.RoleQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 7:02 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class RoleQueryForm extends BaseQueryForm<RoleQueryParam> {

    @ApiModelProperty(value = "role code")
    private String code;

    @ApiModelProperty(value = "role name")
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "query start time must be less than current date")
    @ApiModelProperty(value = "query start time")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "query end time must be less than current time")
    @ApiModelProperty(value = "query end time")
    private Date createdTimeEnd;
}
