package com.shuanger.mysqllockdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shuanger.mysqllockdemo.dao")
@SpringBootApplication
public class MysqlLockDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlLockDemoApplication.class, args);
    }

}
