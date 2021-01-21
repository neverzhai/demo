package com.shuanger.dynamicdemo.staticProxy;

import com.shuanger.democommon.params.CreateUserRequest;
import com.shuanger.democommon.service.OperationRecordService;
import com.shuanger.dynamicdemo.originalObject.UserController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-20 22:32
 * @description: 通过继承的方式实现代理
 */
@RequestMapping("/user/extend")
@RestController
public class ExtendUserControllerProxy extends UserController {

    @Resource
    private OperationRecordService operationRecordService;

    @Override
    @RequestMapping("/create")
    public boolean createUser(@RequestBody CreateUserRequest request) {
        boolean success = super.createUser(request);

        //记录操作日志
        operationRecordService.saveRecord("create a user in extend proxy");
        return success;
    }
}