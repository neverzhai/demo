package com.shuanger.mysqllockdemo.service.impl;

import com.shuanger.mysqllockdemo.domain.TestIndexLockTable;
import com.shuanger.mysqllockdemo.domain.TestLockTableOne;
import com.shuanger.mysqllockdemo.dao.TestLockTableOneMapper;
import com.shuanger.mysqllockdemo.exception.BusinessException;
import com.shuanger.mysqllockdemo.service.ITestIndexLockTableService;
import com.shuanger.mysqllockdemo.service.ITestLockTableOneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;

/**
 * <p>
 * 测试锁表 服务实现类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
@Slf4j
@Service
public class TestLockTableOneServiceImpl extends ServiceImpl<TestLockTableOneMapper, TestLockTableOne> implements ITestLockTableOneService {

    @Resource
    private ITestIndexLockTableService indexLockTableService;

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    @Resource
    private TestLockTableOneMapper lockTableOneMapper;

    @Override
    public boolean updateTableOne(String customId, String name) {

        boolean result = updateTableOneInternal(customId, name);

        return result;
    }

    private boolean updateTableOneInternal(String customId, String name) {

        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            TestIndexLockTable indexLockTable = indexLockTableService.queryIndexTable(customId);
           lockTableOneMapper.updateTableOneInternal(customId, name);
           log.info("更新before commit");
           dataSourceTransactionManager.commit(transaction);

        } catch (BusinessException exception) {
            log.error("更新数据异常");
            dataSourceTransactionManager.rollback(transaction);
        } catch (Exception ex) {
            log.error("更新数据异常: {}", ex.getMessage());
            throw  new BusinessException(400, "更新数据异常了");
        }

        return false;
    }
}
