package com.mk.app.backend.business.service;

import com.mk.app.backend.business.model.LoginUser;
import com.mk.app.backend.business.utils.CacheUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @date: 2024/4/27
 **/
@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        // 用户验证
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登录失败");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        //把完整的用户信息存入缓存  userid作为key
        CacheUtil.put("user:" + userId, loginUser);

        // 生成token
        return loginUser.getToken();
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        Long userId = loginUser.getUser().getId();
        CacheUtil.remove("user:" + userId);
    }
}
