package com.fastdata.gateway.admin.entity.form;

import com.fastdata.common.web.entity.form.BaseQueryForm;
import com.fastdata.gateway.admin.entity.param.GatewayRouteQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 3:53 PM
 * @Version: 1.0
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class GatewayRouteQueryForm extends BaseQueryForm<GatewayRouteQueryParam> {

    @ApiModelProperty(value = "uri path", required = true)
    private String uri;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "query start time must less than current time")
    @ApiModelProperty(value = "query start time")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "query end time must less than current time")
    @ApiModelProperty(value = "query end time")
    private Date createdTimeEnd;
}
