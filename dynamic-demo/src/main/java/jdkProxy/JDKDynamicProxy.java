package jdkProxy;

import originalObject.UserService;
import originalObject.UserService2;
import originalObject.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 11:50
 * @description:
 */
public class JDKDynamicProxy {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        Class<?>[] interfaces = userService.getClass().getInterfaces();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(userService);

        // classLoader 是做什么用的, 选择的原则是什么?
        //多个interface, 难道都实现, 是根据我调用的方法来确定要实现的哪个接口吗
        //
        UserService proxyInstance = (UserService)Proxy.newProxyInstance(JDKDynamicProxy.class.getClassLoader(), interfaces, proxyHandler);
        String userThree = proxyInstance.createUser("user three");
        System.out.println(userThree);

        UserService2 proxyInstance1 = (UserService2)Proxy.newProxyInstance(JDKDynamicProxy.class.getClassLoader(), interfaces, proxyHandler);
        String userfour = proxyInstance1.insertUser("user four");
        System.out.println(userfour);
    }
}
