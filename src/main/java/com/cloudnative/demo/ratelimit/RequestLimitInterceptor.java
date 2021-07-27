package com.cloudnative.demo.ratelimit;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流拦截器
 * @author fguohao
 * @date 2021/07/27
 */
@Component
public class RequestLimitInterceptor implements HandlerInterceptor {

    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PrintWriter out = null;
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod)handler;
                RequestLimit rateLimit = handlerMethod.getMethodAnnotation(RequestLimit.class);
                // 判断是否有注解
                if (rateLimit != null) {
                    // 获取请求url
                    String url = request.getRequestURI();
                    RateLimiter rateLimiter;
                    // 判断map集合中是否有创建好的令牌桶
                    if (!rateLimiterMap.containsKey(url)) {
                        // 创建令牌桶,以n r/s往桶中放入令牌
                        rateLimiter = new LeakyRateLimiter(rateLimit.count());
                        rateLimiterMap.put(url, rateLimiter);
                    }
                    rateLimiter = rateLimiterMap.get(url);
                    // 获取令牌
                    boolean acquire = rateLimiter.canAcquire(url);
                    if (acquire) {
                        return true;
                    } else {
                        System.out.println("请求被限流,url:"+ request.getServletPath());
                        response.setStatus(429);
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        out = response.getWriter();
                        out.println("Too many requests");
                        return false;

                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}

