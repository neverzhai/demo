package com.shuanger.rocketmqdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shuanger.rocketmqdemo.dao")
@SpringBootApplication
public class RocketmqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqDemoApplication.class, args);
    }

}
