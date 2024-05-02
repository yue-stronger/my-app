package com.mk.app.backend.business.security;

import com.mk.app.backend.business.model.LoginUser;
import com.mk.app.backend.business.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 通过 username 查询数据账户密码，验证是否成功
        // 这边先暂定写死 username = admin, password = 123456, id = 1

        User user = new User("admin", new BCryptPasswordEncoder().encode("123456"));
        user.setId(1L);

        return new LoginUser(user);
    }
}
