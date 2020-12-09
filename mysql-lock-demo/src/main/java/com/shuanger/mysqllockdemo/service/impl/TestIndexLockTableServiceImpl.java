package com.shuanger.mysqllockdemo.service.impl;


import com.shuanger.mysqllockdemo.domain.TestIndexLockTable;
import com.shuanger.mysqllockdemo.dao.TestIndexLockTableMapper;
import com.shuanger.mysqllockdemo.service.ITestIndexLockTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 测试index对锁的影响 服务实现类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
@Service
public class TestIndexLockTableServiceImpl extends ServiceImpl<TestIndexLockTableMapper, TestIndexLockTable> implements ITestIndexLockTableService {

    @Resource
    private TestIndexLockTableMapper indexLockTableMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateData(String customId, String name) {
        int count = indexLockTableMapper.updateData(customId, name);
        return count > 0;
    }
}
