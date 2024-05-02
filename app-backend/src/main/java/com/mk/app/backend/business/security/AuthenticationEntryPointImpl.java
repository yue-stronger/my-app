package com.mk.app.backend.business.security;

import com.alibaba.fastjson2.JSON;
import com.mk.app.backend.business.utils.AntdResult;
import com.mk.app.backend.business.utils.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: chenyang.yue
 * @date: 2024/5/2
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        AntdResult result = AntdResult.error(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "认证失败请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
