package com.fastdata.sysadmin.organization.entity.vo;

import com.fastdata.common.web.entity.vo.BaseVo;
import com.fastdata.sysadmin.organization.entity.po.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Set;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/31/21 5:38 PM
 * @Version: 1.0
 * @Description:
 **/
@Data
@NoArgsConstructor
public class UserVo extends BaseVo<User> {
    public UserVo(User user) {
        BeanUtils.copyProperties(user, this);
    }

    private String name;
    private String mobile;
    private String username;
    private String desc;
    private String deleted;
    private Set<String> roleIds;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
