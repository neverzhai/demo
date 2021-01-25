package originalObject;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 10:10
 * @description:
 */
public class UserServiceImpl implements UserService, UserService2 {

    @Override
    public String createUser(String username) {
        return "[create user]: " + username;
    }

    @Override
    public String insertUser(String username) {

        return "[insert User]" + username;
    }
}
