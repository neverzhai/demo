package originalObject;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-22 10:10
 * @description:
 */
public class UserServiceImpl implements UserService {

    @Override
    public String createUser(String username) {
        return "[create user]: " + username;
    }
}
