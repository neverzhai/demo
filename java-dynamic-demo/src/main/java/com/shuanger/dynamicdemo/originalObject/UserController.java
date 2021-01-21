package com.shuanger.dynamicdemo.originalObject;

import com.shuanger.democommon.params.CreateUserRequest;
import com.shuanger.democommon.service.UserInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 15:17
 * @description:
 */
@Component
public class UserController implements IUserController {
    @Resource
    private UserInfoService userInfoService;

    @Override
    public boolean createUser(@RequestBody CreateUserRequest request) {
        return userInfoService.createUser(request);
    }
}
