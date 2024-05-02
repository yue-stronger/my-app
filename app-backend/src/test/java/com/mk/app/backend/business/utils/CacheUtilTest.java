package com.mk.app.backend.business.utils;

import org.junit.Test;

/**
 * @description: TODO
 * @author: chenyang.yue
 * @date: 2024/5/2
 **/
public class CacheUtilTest {

    @Test
    public void put() {
        CacheUtil.put("test", "test");
        String test = (String) CacheUtil.get("test");
        System.out.println(test);
    }

    @Test
    public void get() {
    }
}