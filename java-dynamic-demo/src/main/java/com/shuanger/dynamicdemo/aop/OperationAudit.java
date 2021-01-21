package com.shuanger.dynamicdemo.aop;

import java.lang.annotation.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 14:30
 * @description:
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationAudit {

   String value();
}
