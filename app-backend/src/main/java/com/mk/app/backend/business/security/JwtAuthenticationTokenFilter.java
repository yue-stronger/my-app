package com.mk.app.backend.business.security;

import com.mk.app.backend.business.model.LoginUser;
import com.mk.app.backend.business.utils.CacheUtil;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @date: 2024/5/1
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException { //获取token
//        String token = request.getHeader("token");
        String token = request.getHeader("Authorization");


        // TODO 登录如果也有token怎么办，并且是已经过期的
        //放行
        if (!StringUtils.hasText(token) || Objects.equals(request.getRequestURI(), "/api/login/account")) {
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        Claims claims = JwtUtil.parseJWT(token);
        String userId = claims.getSubject();

        LoginUser loginUser = (LoginUser) CacheUtil.get("user:" + userId);
        // 判断缓存中是否有用户信息
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("已过期，请重新登陆");
        }

        // token 校验
        if (!Objects.equals(loginUser.getToken(), token)){
            throw new RuntimeException("已过期，请重新登陆");
        }

        // 存入SecurityContextHolder
        // TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}