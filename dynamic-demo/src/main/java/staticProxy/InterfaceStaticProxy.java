package staticProxy;

import originalObject.UserService;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 10:08
 * @description:
 */
public class InterfaceStaticProxy implements UserService {

    private final UserService userService;

    public InterfaceStaticProxy(UserService echoService) {
        this.userService = echoService;
    }

    @Override
    public String createUser(String username) {
        long startTime = System.currentTimeMillis();
        String result = userService.createUser(username);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("基于接口实现的静态代理, 创建用户耗时：" + costTime + " ms.");
        return result;
    }
}
