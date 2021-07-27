package com.cloudnative.demo.config;

import com.cloudnative.demo.ratelimit.RequestLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author fguohao
 * @date 2021/07/27
 */
@Configuration
public class RateLimitConfiguration implements WebMvcConfigurer {
    @Resource
    private RequestLimitInterceptor requestLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLimitInterceptor).addPathPatterns("/**");
    }
}
