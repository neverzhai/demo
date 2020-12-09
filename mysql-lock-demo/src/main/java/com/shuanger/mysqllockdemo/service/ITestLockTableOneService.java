package com.shuanger.mysqllockdemo.service;


import com.shuanger.mysqllockdemo.domain.TestLockTableOne;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 测试锁表 服务类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
public interface ITestLockTableOneService extends IService<TestLockTableOne> {

    boolean updateTableOne(String customId, String name);
}
