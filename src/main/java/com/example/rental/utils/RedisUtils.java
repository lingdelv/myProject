package com.example.rental.utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类，提供对Redis基本操作的支持，如设置、获取和删除键值对。
 */
@Component
public class RedisUtils {

    /**
     * 注入StringRedisTemplate，用于操作Redis中的字符串类型数据。
     * 注意：此处的@Autowired是冗余的，因为已经通过@Resource注解注入了。
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 将给定的键值对存储到Redis中，并设置过期时间。
     *
     * @param key 键
     * @param value 值
     * @param timeOut 过期时间，单位为秒
     */
    public void set(String key, String value,Long timeOut){
        stringRedisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }

    /**
     * 从Redis中获取指定键的值。
     *
     * @param key 键
     * @return 键对应的值，如果键不存在则返回null
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 从Redis中删除指定的键。
     *
     * @param key 要删除的键
     */
    public void delete(String key){
        stringRedisTemplate.delete(key);
    }
}

