package com.shuanger.dynamicdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 11:38
 * @description:
 */
@MapperScan("com.shuanger.democommon.dao")
@ComponentScan( basePackages = {"com.shuanger.democommon.service", "com.shuanger.dynamicdemo"})
@SpringBootApplication
public class DynamicDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDemoApplication.class, args);
    }

}
