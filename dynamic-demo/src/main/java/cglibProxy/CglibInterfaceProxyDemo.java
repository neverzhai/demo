package cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import originalObject.UserService;

import java.lang.reflect.Method;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-25 11:35
 * @description: 使用cglib来代理接口, 其实就是实现了这个接口
 */
public class CglibInterfaceProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        // 使用接口创建代理
        Class<UserService> serviceClass = UserService.class;
        enhancer.setInterfaces(new Class[]{serviceClass});

        enhancer.setCallback(new MethodInterceptor() {

            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();

                String result = "[create User]:" + args[0];
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("[CGLIB 字节码提升]动态代理接口实现, 创建用户耗时：" + costTime + " ms.");
                return result;
            }
        });

        UserService o = (UserService)enhancer.create();
        String userFour = o.createUser("user five");
        System.out.println(userFour);

    }
}
