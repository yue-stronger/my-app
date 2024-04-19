package com.mk.app.frontend.init.controller;

import lombok.Data;

/**
 * @date: 2024/4/19
 **/
@Data
public class UserInfo {
    private String name;
    private String avatar;
    private String userid;
    private String email;
    private String signature;
    private String title;
    private String group;
    private Integer notifyCount;
    private Integer unreadCount;
    private String country;
    private String address;
    private String phone;
    private String access;

}
