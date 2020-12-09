package com.shuanger.mysqllockdemo.controller;



import com.shuanger.mysqllockdemo.params.UpdateDataRequest;
import com.shuanger.mysqllockdemo.service.ITestLockTableOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 测试锁表 前端控制器
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
@Slf4j
@RestController
@RequestMapping("/mysqlDemo/test2")
public class TestLockTableOneController {

    @Resource
    private ITestLockTableOneService lockTableOneService;

    @PostMapping("/update")
    public Boolean updateTableOne(@RequestBody UpdateDataRequest request) {
        lockTableOneService.updateTableOne(request.getCustomId(), request.getName());
        return true;
    }

}

