package com.mk.app.backend.business.model;

import lombok.Data;

/**
 * @date: 2024/5/1
 **/
@Data
public class User {

    private Long id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
