package com.shuanger.wechat.inteceptor;

import com.shuanger.wechat.config.AppConstants;

import java.lang.annotation.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 18:02
 * @description:
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DuplicateLock {

    String prefix() default AppConstants.DUPLICATE_LOCK_PREFIX;

    String key() default "";

    int expire() default 1;
}
