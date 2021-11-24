package com.fastdata.gateway.admin.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.gateway.admin.entity.po.FilterDefinition;
import com.fastdata.gateway.admin.entity.po.GatewayRoute;
import com.fastdata.gateway.admin.entity.po.PredicateDefinition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 3:34 PM
 * @Version: 1.0
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
@Slf4j
public class GatewayRouteForm extends BaseForm<GatewayRoute> {

    @NotEmpty(message = "gateway predicte can't be empty")
    @ApiModelProperty(value = "Gateway predicate")
    private List<PredicateDefinition> predicates = new ArrayList<>();

    @ApiModelProperty(value = "gateway filter")
    private List<FilterDefinition> filters = new ArrayList<>();

    @NotBlank(message = "uri can't be blank")
    @ApiModelProperty(value = "Gateway uri")
    private String uri;

    @NotBlank(message = "route id can't be blank")
    @ApiModelProperty(value = "Gateway route id")
    private String routeId;

    @ApiModelProperty(value = "sort")
    private Integer orders = 0;

    @ApiModelProperty(value = "gateway route desc")
    private String description;

    @Override
    public GatewayRoute toPo(Class<GatewayRoute> clazz) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(this, gatewayRoute);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            gatewayRoute.setFilters(objectMapper.writeValueAsString(this.getFilters()));
            gatewayRoute.setPredicates(objectMapper.writeValueAsString(this.getPredicates()));
        } catch (JsonProcessingException e) {
            log.error("Gateway filter or predicates error", e);
        }

        return gatewayRoute;
    }
}
