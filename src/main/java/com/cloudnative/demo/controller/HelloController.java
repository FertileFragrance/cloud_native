package com.cloudnative.demo.controller;


import com.cloudnative.demo.ratelimit.RequestLimit;
import com.cloudnative.demo.service.HelloService;
import com.cloudnative.demo.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    @RequestLimit(count = 10000)
    public ResultVO getHello() {
        return helloService.getHello();
    }

}
