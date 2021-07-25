package com.cloudnative.demo.service;

import com.cloudnative.demo.vo.ResultVO;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public ResultVO getHello() {
        return new ResultVO(200, "ok", "hello");
    }

}
