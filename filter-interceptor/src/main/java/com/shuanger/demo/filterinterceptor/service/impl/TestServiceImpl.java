package com.shuanger.demo.filterinterceptor.service.impl;

import com.shuanger.demo.filterinterceptor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-08 16:24
 * @description: test service
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getName() {
       return "test Service";
    }
}
