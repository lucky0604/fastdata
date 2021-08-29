package com.fastdata.common.web.interceptor;

import brave.internal.Nullable;
import com.fastdata.common.core.util.UserContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/28/21 11:37 PM
 * @Version: 1.0
 * @Description:
 **/

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    /**
     * the user token between the services
     * {
     *     "user_name": "required",
     *     "custom_key": "value"
     * }
     */
    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    /**
     * the verify token between the services
     */
    public static final String X_CLIENT_TOKEN = "x-client-token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get the token from the gateway and verify. if it is valid then the info inside of the x-client-token-user is true
        checkToken(request.getHeader(X_CLIENT_TOKEN));
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER), "{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString, Map.class));
        return true;
    }

    private void checkToken(String token) {
        // TODO: check token
        log.debug("verify token: {}", token);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}
