package com.mk.app.backend.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/api/demo1")
    public String demo1() {
        return "hello demo1";
    }

    @RequestMapping("/demo2")
    public String demo2() {
        return "hello demo2";
    }
}
