package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 11:52
 * @description:
 */
public class DynamicProxyHandler implements InvocationHandler {

    private final Object proxyObject;

    public DynamicProxyHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = method.invoke(proxyObject, args);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("JDK 动态代理实现, 创建用户耗时:" + costTime + "ms");
        return result;
    }
}
