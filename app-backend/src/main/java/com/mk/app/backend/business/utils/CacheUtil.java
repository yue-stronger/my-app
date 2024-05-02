package com.mk.app.backend.business.utils;

/**
 * @date: 2024/5/1
 **/

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * 用于提供内存缓存服务的实现类 使用Google Guava库中的CacheBuilder创建缓存对象
 *
 * @author stronger
 */
@Service
public class CacheUtil {

    /**
     * 内存缓存对象，使用Google Guava的CacheBuilder进行配置和构建
     */
    private static Cache<String, Object> cache = CacheBuilder.newBuilder()
            // 初始容量
            .initialCapacity(500)
            // 最大容量，超过时按LRU（最近最少使用）算法清除缓存项
            .maximumSize(2000)
            // 并发级别，用于并发访问控制
            .concurrencyLevel(8)
            // 写入后过期时间，单位为分钟
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    /**
     * 将键值对存入缓存
     *
     * @param key   缓存键
     * @param value 缓存值
     */
    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * 根据键获取缓存值
     *
     * @param key 缓存键
     * @return 对应的缓存值，如果不存在则返回null
     */
    public static Object get(String key) {
        return cache.getIfPresent(key);
    }

    /**
     * 清除key
     * @param key
     */
    public static void remove(String key) {
        cache.invalidate(key);
    }
}
