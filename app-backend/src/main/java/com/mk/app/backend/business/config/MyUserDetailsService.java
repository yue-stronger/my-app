package com.mk.app.backend.business.config;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //点击页面的登陆时,你实际需要做的事为
        //1.拿着用户名去数据库里查询其密码
        //2.将拿到的用户名和密码封装到User里进行返回

        log.info("用户名为:" + username);
        //假设下面的密码是根据用户名获得的
        String password = passwordEncoder.encode("123456");
        log.error("password:" + password);
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

