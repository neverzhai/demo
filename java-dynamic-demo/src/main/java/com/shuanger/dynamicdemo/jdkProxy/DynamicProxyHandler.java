package com.shuanger.dynamicdemo.jdkProxy;

import com.shuanger.democommon.service.OperationRecordService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 16:28
 * @description:
 */
public class DynamicProxyHandler implements InvocationHandler {

    private OperationRecordService operationRecordService;

    private Object proxiedObject;

    public DynamicProxyHandler(Object proxiedObject, OperationRecordService recordService) {
        this.proxiedObject = proxiedObject;
        this.operationRecordService = recordService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(proxiedObject, args);
        operationRecordService.saveRecord("create a user jdk");

        return result;
    }
}
