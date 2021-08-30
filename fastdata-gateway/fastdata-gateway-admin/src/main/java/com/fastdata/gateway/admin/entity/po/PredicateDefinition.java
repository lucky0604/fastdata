package com.fastdata.gateway.admin.entity.po;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/30/21 3:32 PM
 * @Version: 1.0
 * @Description:
 **/
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredicateDefinition {

    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
