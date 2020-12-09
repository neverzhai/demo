package com.shuanger.mysqllockdemo.service.impl;

import com.shuanger.mysqllockdemo.domain.TestLockTableOne;
import com.shuanger.mysqllockdemo.dao.TestLockTableOneMapper;
import com.shuanger.mysqllockdemo.service.ITestLockTableOneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试锁表 服务实现类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
@Service
public class TestLockTableOneServiceImpl extends ServiceImpl<TestLockTableOneMapper, TestLockTableOne> implements ITestLockTableOneService {

}
