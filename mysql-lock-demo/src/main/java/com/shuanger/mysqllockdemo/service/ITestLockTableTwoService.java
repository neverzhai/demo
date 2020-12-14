package com.shuanger.mysqllockdemo.service;


import com.shuanger.mysqllockdemo.domain.TestLockTableTwo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 测试index对锁的影响 服务类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
public interface ITestLockTableTwoService extends IService<TestLockTableTwo> {

    boolean updateData(String customId, String name);

    TestLockTableTwo queryIndexTable(String customId);
}
