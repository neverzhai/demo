package com.shuanger.javadate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shuanger.javadate.dao")
@SpringBootApplication
public class JavaDateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDateApplication.class, args);
    }

}
