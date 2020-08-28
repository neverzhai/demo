package com.shuanger.javadate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.shuanger.javadate.dao")
@EnableAsync
@SpringBootApplication
public class JavaDateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDateApplication.class, args);
    }

}
