package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseQueryForm;
import com.fastdata.sysadmin.organization.entity.param.ResourceQueryParam;
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
 * @Date: 8/31/21 6:55 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class ResourceQueryForm extends BaseQueryForm<ResourceQueryParam> {

    @ApiModelProperty(value = "resource name")
    private String name;

    @ApiModelProperty(value = "resource code")
    private String code;

    @ApiModelProperty(value = "resource url")
    private String url;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "query start time must be less than current date")
    @ApiModelProperty(value = "query start time")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "query start time must be less than current date")
    @ApiModelProperty(value = "query end time")
    private Date createdTimeEnd;
}
