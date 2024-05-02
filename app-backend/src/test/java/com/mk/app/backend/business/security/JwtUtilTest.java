package com.mk.app.backend.business.security;

import org.junit.Test;

/**
 * @date: 2024/5/1
 **/
public class JwtUtilTest {

    @Test
    public void testCreateJWT1() {
        String jwt = JwtUtil.createJWT("1234555");
        System.out.println(jwt);
    }

    @Test
    public void testCreateJWT2() throws InterruptedException {
        String jwt = JwtUtil.createJWT("1234555", 2000L);
        System.out.println(jwt);
//        Thread.sleep(2000L);
        System.out.println(JwtUtil.parseJWT(jwt));
    }

    @Test
    public void testCreateJWT3() {
    }

    @Test
    public void testParseJWT() {
    }
}