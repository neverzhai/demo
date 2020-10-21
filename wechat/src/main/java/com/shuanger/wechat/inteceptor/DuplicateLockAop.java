package com.shuanger.wechat.inteceptor;

import com.shuanger.wechat.cache.ICacheService;
import com.shuanger.wechat.config.AppConstants;
import com.shuanger.wechat.response.BusinessCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 18:06
 * @description:
 */
@Aspect
@Slf4j
@Component
public class DuplicateLockAop {

    @Autowired
    private ICacheService cacheService;

    @Around("@annotation(annotation)&&@annotation(com.shuanger.wechat.inteceptor.DuplicateLock")
    public Object doAroundCache(ProceedingJoinPoint pjp, DuplicateLock annotation) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Object[] args = pjp.getArgs();
        String parsedKey = SpelKeyPasser.parseExpressionKey(annotation.key(), args);

        if (null == parsedKey) {
            return pjp.proceed();
        }
        String redisKey = String.format("%s_%s_%s_%s", annotation.prefix(), pjp.getTarget().getClass().getSimpleName(),
                method.getName(), parsedKey);

        log.info("Duplication Lock AOP --key:{}", redisKey);

        boolean success = true;
        try {
            success = cacheService.setNX(redisKey, AppConstants.CACHED, annotation.expire());
            log.info("cache result key :{} value :{}", redisKey, success);
        } catch (Exception e) {
            log.error("redis setNX 操作异常：", e);
        }

        Assert.isTrue(success, BusinessCode.DUPLICATE_REQUEST.getMsg());

        return pjp.proceed();
    }
}
