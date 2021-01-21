package com.shuanger.dynamicdemo.aop;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 14:24
 * @description: 使用apo记录log
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class OperationAop {

    @Around("@annotation(annotation)&&@annotation(com.shuanger.dynamicdemo.aop.OperationAudit)")
    public Object doAroundCache(ProceedingJoinPoint pjp, OperationAudit annotation) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Object[] args = pjp.getArgs();

        String methodName = String.format("%s.%s", pjp.getTarget().getClass().getSimpleName(), method.getName());

        try {
            return pjp.proceed();
        } finally {
            saveRecord(methodName,annotation.value());
        }
    }

    private void saveRecord(String methodName, String value) {
        log.info("将操作日志写入数据库value: {}", value);

    }
}
