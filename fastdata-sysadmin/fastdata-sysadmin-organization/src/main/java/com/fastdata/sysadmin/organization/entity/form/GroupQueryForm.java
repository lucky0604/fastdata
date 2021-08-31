package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseQueryForm;
import com.fastdata.sysadmin.organization.entity.param.GroupQueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 6:29 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class GroupQueryForm extends BaseQueryForm<GroupQueryParam> {
}
