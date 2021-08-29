package com.fastdata.authorization.service;

import com.fastdata.authorization.entity.User;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 11:08 PM
 * @Version: 1.0
 * @Description:
 **/
@Service
public interface IUserService {

    @Cacheable(value = "#id")
    User getByUniqueId(String uniqueId);
}
