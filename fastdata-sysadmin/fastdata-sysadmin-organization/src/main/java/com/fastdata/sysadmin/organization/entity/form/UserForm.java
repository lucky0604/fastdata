package com.fastdata.sysadmin.organization.entity.form;

import com.fastdata.common.web.entity.form.BaseForm;
import com.fastdata.sysadmin.organization.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 7:06 PM
 * @Version: 1.0
 * @Description:
 **/
@ApiModel
@Data
public class UserForm extends BaseForm<User> {

    @ApiModelProperty(value = "user name")
    @NotBlank(message = "username can't be blank")
    @Length(min = 3, max = 20, message = "username should between 3-20 chars")
    private String username;

    @ApiModelProperty(value = "password")
    @NotBlank(message = "password can't be blank")
    @Length(min = 5, max = 20, message = "password should between 5-20 chars")
    private String password;

    @ApiModelProperty(value = "phone number")
    @NotBlank(message = "phone number can't be blank")
    private String mobile;

    @ApiModelProperty(value = "real name")
    @NotBlank(message = "real name can't be blank")
    private String name;

    @ApiModelProperty(value = "desc")
    private String desc;

    @ApiModelProperty(value = "user roles")
    private Set<String> roleIds;

    @ApiModelProperty(value = "user status, true is active")
    private Boolean enabled = true;

    @ApiModelProperty(value = "account expiration, true is not yet")
    private Boolean accountNonExpired = true;

    @ApiModelProperty(value = "credentials expiration, true is not yet")
    private Boolean credentialsNonExpired = true;

    @ApiModelProperty(value = "account lock status, true is not yet")
    private Boolean accountNonLocked = true;
}
