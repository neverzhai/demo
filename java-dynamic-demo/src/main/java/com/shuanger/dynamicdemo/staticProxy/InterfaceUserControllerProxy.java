package com.shuanger.dynamicdemo.staticProxy;

import com.shuanger.democommon.params.CreateUserRequest;
import com.shuanger.democommon.service.OperationRecordService;
import com.shuanger.dynamicdemo.originalObject.IUserController;
import com.shuanger.dynamicdemo.originalObject.UserController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-20 22:29
 * @description: 基于实现接口实现动态代理
 *              适用于有接口的情况下， 如果我们使用的第三方库，而要代理的对象并没有定义接口则无法使用， 需要通过继承类来实现
 */
@RequestMapping("/user")
@RestController
public class InterfaceUserControllerProxy implements IUserController {

    @Resource
    private UserController userController;

    @Resource
    private OperationRecordService operationRecordService;

    @Override
    @PostMapping("/create")
    public boolean createUser(@RequestBody CreateUserRequest request) {
        boolean success = userController.createUser(request);

        // 记录操作日志
        operationRecordService.saveRecord("create a user");

        return success;
    }
}