package com.fastdata.auth.client.service.impl;

import com.fastdata.auth.client.provider.AuthProvider;
import com.fastdata.auth.client.service.IAuthService;
import com.fastdata.common.core.entity.vo.Result;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 9:51 PM
 * @Version: 1.0
 * @Description:
 **/
@Service
@Slf4j
public class AuthService implements IAuthService {

    private static final String BEARER = "Bearer ";

    @Autowired
    private AuthProvider authProvider;

    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(Result authResult) {
        log.debug("Verify Permission: {}", authResult.getData());
        return authResult.isSuccess() && (boolean) authResult.getData();
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        if (StringUtils.isBlank(authentication) || !authentication.startsWith(BEARER)) {
            log.error("user token is null");
            return Boolean.FALSE;
        }
        if (invalidJwtAccessToken(authentication)) {
            return Boolean.FALSE;
        }
        return hasPermission(authenticate(authentication, url, method));
    }

    @Override
    public Jws<Claims> getJwt(String jwtToken) {
        if (jwtToken.startsWith(BEARER)) {
            jwtToken = StringUtils.substring(jwtToken, BEARER.length());
        }
        return Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(jwtToken);
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        boolean invalid = Boolean.TRUE;
        try {
            getJwt(authentication);
            invalid = Boolean.FALSE;
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException ex) {
            log.error("user token error: {}", ex.getMessage());
        }
        return invalid;
    }
}
