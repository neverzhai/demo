package com.shuanger.mysqljoindemo.service;

import com.shuanger.mysqljoindemo.domain.TestEmployeeInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuanger.mysqljoindemo.dto.EmployeeInfoDto;

import java.util.List;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
public interface ITestEmployeeInfoService extends IService<TestEmployeeInfo> {

    List<EmployeeInfoDto> getEmployeeInfoWithJoin();

    List<EmployeeInfoDto> getEmployeeWithoutJoin();

}
