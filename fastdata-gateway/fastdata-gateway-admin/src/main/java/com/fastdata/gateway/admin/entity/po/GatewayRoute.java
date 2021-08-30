package com.fastdata.gateway.admin.entity.po;

import com.fastdata.common.web.entity.po.BasePo;
import lombok.*;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 3:30 PM
 * @Version: 1.0
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute extends BasePo {
    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String desc;
    private Integer orders = 0;
    private String status = "Y";
}
