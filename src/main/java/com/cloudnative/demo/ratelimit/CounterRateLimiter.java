package com.cloudnative.demo.ratelimit;

/**
 * @author fguohao
 * @date 2021/07/27
 */
public class CounterRateLimiter extends RateLimiter{

    public long timeStamp = System.currentTimeMillis();
    public int reqCount = 0;
    public final long interval = 1000; // 时间窗口ms

    public CounterRateLimiter(long count) {
        super(count);
    }

    @Override
    public boolean canAcquire(String key) {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            // 在时间窗口内
            reqCount++;
            // 判断当前时间窗口内是否超过最大请求控制数
            return reqCount <= count;
        }
        else {
            timeStamp = now;
            // 超时后重置
            reqCount = 1;
            return true;
        }
    }
}
