package com.fastdata.auth.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 1:48 AM
 * @Version: 1.0
 * @Description:
 **/

@Service
public interface IAuthenticationService {

    boolean decide(HttpServletRequest authRequest);
}
