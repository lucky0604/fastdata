package com.fastdata.common.web.entity.vo;

import com.fastdata.common.web.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 12:05 AM
 * @Version: 1.0
 * @Description:
 **/

@Data
@NoArgsConstructor
public class BaseVo<T extends BasePo> implements Serializable {

    private String id;
}
