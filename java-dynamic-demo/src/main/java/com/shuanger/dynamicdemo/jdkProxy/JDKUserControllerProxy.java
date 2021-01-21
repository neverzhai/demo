package com.shuanger.dynamicdemo.jdkProxy;

import com.shuanger.democommon.params.CreateUserRequest;
import com.shuanger.democommon.service.OperationRecordService;
import com.shuanger.dynamicdemo.originalObject.IUserController;
import com.shuanger.dynamicdemo.originalObject.UserController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 11:09
 * @description: java jdk 动态代理
 */
@RequestMapping("/user/jdk")
@RestController
public class JDKUserControllerProxy {

    @Resource
    private UserController userController;

    @Resource
    private OperationRecordService operationRecordService;

    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject,operationRecordService);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
    }

    @RequestMapping("/create")
    public boolean createUser(@RequestBody CreateUserRequest request) {
        IUserController proxy = (IUserController)createProxy(userController);
        return proxy.createUser(request);
    }
}
