package com.mk.app.frontend.init.controller;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {


    @GetMapping("/api/rule")
    public String rule(HttpServletRequest request, HttpServletResponse response) {


        // 测试重新登陆
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("errorCode", "100403");
        map.put("errorMessage", "请重新登陆");

        return JSON.toJSONString(map);
    }
}
