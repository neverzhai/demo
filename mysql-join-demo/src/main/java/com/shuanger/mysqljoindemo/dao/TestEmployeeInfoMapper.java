package com.shuanger.mysqljoindemo.dao;

import com.shuanger.mysqljoindemo.domain.TestEmployeeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuanger.mysqljoindemo.dto.EmployeeInfoDto;

import java.util.List;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
public interface TestEmployeeInfoMapper extends BaseMapper<TestEmployeeInfo> {

    List<EmployeeInfoDto> getEmployeeInfoWithJoin();
}
