package com.fastdata.authorization.oauth2;

import com.fastdata.authorization.entity.User;
import com.fastdata.authorization.provider.SmsCodeProvider;
import com.fastdata.authorization.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 11:25 PM
 * @Version: 1.0
 * @Description:
 **/
@Slf4j
@Service("mobileUserDetailsService")
public class MobileUserDetailsService extends CustomUserDetailsService {

    @Autowired
    private IUserService userService;
    @Autowired
    private SmsCodeProvider smsCodeProvider;

    @Override
    public UserDetails loadUserByUsername(String uniqueId) {
        User user = userService.getByUniqueId(uniqueId);
        log.info("load user by mobile: {}", user.toString());

        String credentials = smsCodeProvider.getSmsCode(uniqueId, "LOGIN");
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                credentials,
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                super.obtainGrantedAuthorities(user)
        );
    }
}
