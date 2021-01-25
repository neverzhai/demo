package cglibProxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import originalObject.ActivityService;

import java.lang.reflect.Method;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 17:03
 * @description: cglib 字节码提升动态代理
 * https://cloud.tencent.com/developer/article/1461796
 * https://blog.csdn.net/starryninglong/article/details/89737419?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.not_use_machine_learn_pai&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.not_use_machine_learn_pai
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();


        // 使用类创建代理
        Class<ActivityService> superClass = ActivityService.class;
        enhancer.setSuperclass(superClass);
        enhancer.setCallback(new MethodInterceptor() {

            // 各个参数要怎么理解呀????
            // 为什么需要cglib
            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                // Source -> CGLIB 子类
                // 目标类  -> DefaultEchoService
                // 错误使用
//                Object result = method.invoke(source, args);
                // 正确的方法调用
                Object result = methodProxy.invokeSuper(source, args);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("[CGLIB 字节码提升]动态代理, 创建活动耗时：" + costTime + " ms.");
                return args[0];
            }
        });

        ActivityService o = (ActivityService)enhancer.create();
        String userFour = o.crateActivity("activity one");
        System.out.println(userFour);

    }
}
