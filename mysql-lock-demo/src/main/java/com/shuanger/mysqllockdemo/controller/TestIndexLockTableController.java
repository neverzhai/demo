package com.shuanger.mysqllockdemo.controller;



import com.shuanger.mysqllockdemo.params.UpdateIndexLockTableRequest;
import com.shuanger.mysqllockdemo.service.ITestIndexLockTableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 测试index对锁的影响 前端控制器
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/mysqlDemo/test")
public class TestIndexLockTableController {

    @Resource
    private ITestIndexLockTableService testIndexLockTableService;


    @PostMapping("/update")
    public Boolean updateData(@RequestBody UpdateIndexLockTableRequest request) {

        testIndexLockTableService.updateData(request.getCustomId(), request.getName());
        return true;
    }

}

