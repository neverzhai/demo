package com.shuanger.mysqljoindemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shuanger.mysqljoindemo.domain.TestDepartmentInfo;
import com.shuanger.mysqljoindemo.dao.TestDepartmentInfoMapper;
import com.shuanger.mysqljoindemo.service.ITestDepartmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
@Service
public class TestDepartmentInfoServiceImpl extends ServiceImpl<TestDepartmentInfoMapper, TestDepartmentInfo> implements ITestDepartmentInfoService {

    @Override
    public List<TestDepartmentInfo> getAllDepartInfo() {
        LambdaQueryWrapper<TestDepartmentInfo> queryWrapper = new QueryWrapper<TestDepartmentInfo>().lambda()
                .eq(TestDepartmentInfo::getDeleted, false);

        return list(queryWrapper);
    }
}
