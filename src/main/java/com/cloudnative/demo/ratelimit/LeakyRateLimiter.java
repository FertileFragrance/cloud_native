package com.cloudnative.demo.ratelimit;

/**
 * @author fguohao
 * @date 2021/07/27
 */
public class LeakyRateLimiter extends RateLimiter{

    public long timeStamp = System.currentTimeMillis();
    public long capacity;
    public double water;

    public LeakyRateLimiter(long count) {
        super(count);
    }

    @Override
    boolean canAcquire(String key) {
        if(capacity==0) {
            capacity = count;
        }
        long now = System.currentTimeMillis();

        // 漏水
        System.out.println("now:"+now+"  time:"+timeStamp);
        System.out.println( (water - (now - timeStamp)/1000.0 * count));
        water = Math.max(0d, (double) (water - (now - timeStamp)/1000.0 * count));

        timeStamp = now;
        if ((water + 1) < capacity) {
            // 尝试加水,并且水还未满
            water += 1;
            System.out.println("water:"+water);
            return true;
        }
        else {
            // 水满，拒绝加水
            return false;
        }
    }
}
