package com.shuanger.mysqllockdemo.dao;


import com.shuanger.mysqllockdemo.domain.TestLockTableOne;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 测试锁表 Mapper 接口
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
public interface TestLockTableOneMapper extends BaseMapper<TestLockTableOne> {

    void updateTableOneInternal(@Param("customId") String customId, @Param("name") String name);
}
