package com.shuanger.mysqljoindemo.service;

import com.shuanger.mysqljoindemo.domain.TestDepartmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
public interface ITestDepartmentInfoService extends IService<TestDepartmentInfo> {

    List<TestDepartmentInfo> getAllDepartInfo();

}
