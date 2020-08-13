package com.shuanger.interceptor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-11 11:49
 * @description:
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {


    public String test() {
        return "This is a test service";
    }
}
