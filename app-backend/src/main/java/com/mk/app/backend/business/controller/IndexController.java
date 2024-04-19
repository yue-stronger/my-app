package com.mk.app.backend.business.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

//    @RequestMapping("/user/login")
    public String index() {
        return "hello demo1";
    }

}