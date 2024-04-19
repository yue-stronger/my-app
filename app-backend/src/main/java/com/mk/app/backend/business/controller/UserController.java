package com.mk.app.backend.business.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2024/4/18
 **/
@Slf4j
@RestController("/api")
public class UserController {

    @PostMapping("/login/account")
    public String login(String username, String password){
        log.info("username:{},password:{}",username,password);
        return "{\"status\":\"ok\",\"type\":\"account\",\"currentAuthority\":\"admin\"}";
    }

    @GetMapping("/currentUser")
    public String currentUser(){
        return "{\"status\":\"ok\",\"type\":\"account\",\"currentAuthority\":\"admin\"}";
    }
}
