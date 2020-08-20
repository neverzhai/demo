package com.shuanger.javadate.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 17:41
 * @description:
 */

//@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert field....");
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("modifiedTime", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifiedTime", LocalDateTime.now(),metaObject);
    }
}