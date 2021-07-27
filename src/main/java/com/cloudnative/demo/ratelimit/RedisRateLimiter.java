package com.cloudnative.demo.ratelimit;

import com.cloudnative.demo.config.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author fguohao
 * @date 2021/07/27
 */
public class RedisRateLimiter extends RateLimiter{



    public RedisRateLimiter(long count) {
        super(count);
    }

    @Override
    boolean canAcquire(String key) {
        RedisTemplate redisTemplate = JedisUtil.redisTemplate;
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
        if(redisTemplate.hasKey(key)) {
            Integer reqCount = redisTemplate.opsForZSet().rangeByScore(key, currentTime -  1000, currentTime).size();
            System.out.println(count);
            if (reqCount != null && reqCount >= count) {
                return false;
            }
        }
        redisTemplate.opsForZSet().add(key, UUID.randomUUID().toString(),currentTime);
        return true;
    }
}
