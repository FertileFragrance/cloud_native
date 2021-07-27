package com.cloudnative.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author fguohao
 * @date 2021/07/27
 */
@Component
public class JedisUtil {
   public static RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        JedisUtil.redisTemplate = redisTemplate;
    }

}
