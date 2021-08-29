package com.fastdata.auth.client.service;

import com.fastdata.common.core.entity.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 9:48 PM
 * @Version: 1.0
 * @Description:
 **/

public interface IAuthService {

    Result authenticate(String authentication, String url, String method);

    boolean ignoreAuthentication(String url);

    boolean hasPermission(Result authResult);

    boolean hasPermission(String authentication, String url, String method);

    boolean invalidJwtAccessToken(String authentication);

    Jws<Claims> getJwt(String jwtToken);
}
