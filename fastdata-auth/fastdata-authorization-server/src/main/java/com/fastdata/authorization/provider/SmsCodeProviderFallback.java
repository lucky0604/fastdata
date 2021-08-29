package com.fastdata.authorization.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: lucky
 * @License: (C) Copyright
 * @Contact: lucky_soft@163.com
 * @Date: 8/29/21 11:36 PM
 * @Version: 1.0
 * @Description:
 **/
@Component
public class SmsCodeProviderFallback implements SmsCodeProvider {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String getSmsCode(String mobile, String businessType) {
        return passwordEncoder.encode("123456");
    }
}
