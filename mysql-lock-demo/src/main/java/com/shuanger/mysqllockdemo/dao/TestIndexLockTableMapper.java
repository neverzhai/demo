package com.shuanger.mysqllockdemo.dao;


import com.shuanger.mysqllockdemo.domain.TestIndexLockTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 测试index对锁的影响 Mapper 接口
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
public interface TestIndexLockTableMapper extends BaseMapper<TestIndexLockTable> {

    int updateData(@Param("customId") String customId, @Param("name") String name);
}
