package staticProxy;

import originalObject.UserServiceImpl;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 10:30
 * @description:
 */
public class ExtendStaticProxy extends UserServiceImpl {

    private final UserServiceImpl userService;

    public ExtendStaticProxy(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String createUser(String username) {
        long startTime = System.currentTimeMillis();

        String result = super.createUser(username);

        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("基于继承实现的静态代理, 创建用户耗时:"+ costTime +"ms");
        return  result;
    }
}
