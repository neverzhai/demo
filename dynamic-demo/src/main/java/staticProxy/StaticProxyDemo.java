package staticProxy;

import originalObject.UserServiceImpl;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 11:45
 * @description:
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        InterfaceStaticProxy interfaceStaticProxy = new InterfaceStaticProxy(new UserServiceImpl());
        String userOne = interfaceStaticProxy.createUser("user one");
        System.out.println(userOne);

        ExtendStaticProxy extendStaticProxy = new ExtendStaticProxy(new UserServiceImpl());
        String userTwo = extendStaticProxy.createUser("user two");
        System.out.println(userTwo);
    }
}
