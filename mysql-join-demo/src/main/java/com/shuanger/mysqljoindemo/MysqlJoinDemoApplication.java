package com.shuanger.mysqljoindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shuanger.mysqljoindemo.dao")
@SpringBootApplication
public class MysqlJoinDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlJoinDemoApplication.class, args);
    }

}
