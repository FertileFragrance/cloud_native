package com.cloudnative.demo.ratelimit;

/**
 * @author fguohao
 * @date 2021/07/27
 */
public abstract class RateLimiter {
    protected long count;

    public RateLimiter(long count) {
        this.count = count;
    }

    abstract boolean canAcquire(String key);
}
