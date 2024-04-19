package com.mk.app.frontend.init.controller;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private Map<String, UserInfo> map = new HashMap<String, UserInfo>();


    @RequestMapping("/test")
    public String test() {
        return "hello test";
    }

    @PostMapping("/api/login/account")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();

        Cookie cookie = new Cookie("token", token);
//        token.setDomain("/");
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        UserInfo info = new UserInfo();
        info.setName("Serati Ma");
        info.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        info.setUserid(token);

        map.put(token, info);

        return "{\"status\":\"ok\",\"type\":\"account\",\"currentAuthority\":\"admin\"}";
    }

    @GetMapping("/api/currentUser")
    public String currentUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                System.out.println(cookie.getName() + ":" + cookie.getValue());
            }
            String token = cookie.getValue();
            UserInfo info = map.get(token);
            if (Objects.nonNull(info)) {
                result.put("success", true);
                result.put("data", info);
            }
        }

        return JSON.toJSONString(result);

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("success", true);
//
//        UserInfo info = new UserInfo();
//        info.setName("Serati Ma");
//        info.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
//        info.setUserid("00000001");
//        map.put("data", info);
//
//        return JSON.toJSONString(map);

//        return "{\n"
//                + "      \"success\": true,\n"
//                + "      \"data\": {\n"
//                + "        \"name\": 'Serati Ma',\n"
//                + "        \"avatar\": 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',\n"
//                + "        \"userid\": '00000001',\n"
//                + "        \"email\": 'antdesign@alipay.com',\n"
//                + "        \"signature\": '海纳百川，有容乃大',\n"
//                + "        \"title\": '交互专家',\n"
//                + "        \"group\": '蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED',\n"
//                + "        \"tags\": [\n"
//                + "          {\n"
//                + "            \"key\": '0',\n"
//                + "            \"label\": '很有想法的',\n"
//                + "          },\n"
//                + "          {\n"
//                + "            \"key\": '1',\n"
//                + "            \"label\": '专注设计',\n"
//                + "          },\n"
//                + "          {\n"
//                + "            \"key\": '2',\n"
//                + "            \"label\": '辣~',\n"
//                + "          },\n"
//                + "          {\n"
//                + "            \"key\": '3',\n"
//                + "            \"label\": '大长腿',\n"
//                + "          },\n"
//                + "          {\n"
//                + "            \"key\": '4',\n"
//                + "            \"label\": '川妹子',\n"
//                + "          },\n"
//                + "          {\n"
//                + "            \"key\": '5',\n"
//                + "            \"label\": '海纳百川',\n"
//                + "          },\n"
//                + "        ],\n"
//                + "        \"notifyCount\": 12,\n"
//                + "        \"unreadCount\": 11,\n"
//                + "        \"country\": 'China',\n"
//                + "        \"access\": getAccess(),\n"
//                + "        \"geographic\": {\n"
//                + "          \"province\": {\n"
//                + "            \"label\": '浙江省',\n"
//                + "            \"key\": '330000',\n"
//                + "          },\n"
//                + "          \"city\": {\n"
//                + "            \"label\": '杭州市',\n"
//                + "            \"key\": '330100',\n"
//                + "          },\n"
//                + "        },\n"
//                + "        \"address\": '西湖区工专路 77 号',\n"
//                + "        \"phone\": '0752-268888888',\n"
//                + "      },\n"
//                + "    }";
    }
}
