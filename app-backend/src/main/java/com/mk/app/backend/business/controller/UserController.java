package com.mk.app.backend.business.controller;

import com.alibaba.fastjson2.JSON;
import com.mk.app.backend.business.model.User;
import com.mk.app.backend.business.service.LoginService;
import com.mk.app.backend.business.utils.AntdResult;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2024/4/18
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login/account")
    public AntdResult<String> login(@RequestBody User user, String username, String password, HttpServletResponse response) {
        if (StringUtils.isEmpty(username)){
            username = user.getUsername();
        }
        if (StringUtils.isEmpty(password)){
            password = user.getPassword();
        }
        log.info("username:{},password:{}", username, password);
//        return "{\"status\":\"ok\",\"type\":\"account\",\"currentAuthority\":\"admin\"}";

        String token = loginService.login(username, password);

        Cookie cookie = new Cookie("token", token);
        // 7天
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);

        return AntdResult.success(token);
    }

    /**
     * 查询当前用户
     * @return
     */
    @GetMapping("/currentUser")
    public String currentUser() {
        Map<String, Object> map = new HashMap<>();

        map.put("success", true);

        UserInfo info = new UserInfo();
        info.setName("Serati Ma");
        info.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        info.setUserid("00000001");
        map.put("data", info);

        return JSON.toJSONString(map);

//        return "{\"status\":\"ok\",\"type\":\"account\",\"currentAuthority\":\"admin\"}";
    }

    /**
     * 用户登出
     * @return
     */
    @PostMapping("/logout")
    public AntdResult<String> logout() {
        loginService.logout();
        return AntdResult.success("退出成功");
    }
}
