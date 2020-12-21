package com.shuanger.mysqljoindemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shuanger.mysqljoindemo.domain.TestDepartmentInfo;
import com.shuanger.mysqljoindemo.domain.TestEmployeeInfo;
import com.shuanger.mysqljoindemo.dao.TestEmployeeInfoMapper;
import com.shuanger.mysqljoindemo.dto.EmployeeInfoDto;
import com.shuanger.mysqljoindemo.service.ITestDepartmentInfoService;
import com.shuanger.mysqljoindemo.service.ITestEmployeeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
@Service
public class TestEmployeeInfoServiceImpl extends ServiceImpl<TestEmployeeInfoMapper, TestEmployeeInfo> implements ITestEmployeeInfoService {

    @Resource
    private ITestDepartmentInfoService departmentInfoService;

    @Override
    public List<EmployeeInfoDto> getEmployeeInfoWithJoin() {
        return this.baseMapper.getEmployeeInfoWithJoin();
    }

    @Override
    public List<EmployeeInfoDto> getEmployeeWithoutJoin() {
        LambdaQueryWrapper<TestEmployeeInfo> queryWrapper = new QueryWrapper<TestEmployeeInfo>().lambda()
                .eq(TestEmployeeInfo::getDeleted, false);

        List<TestEmployeeInfo> employeeInfos = list(queryWrapper);

        List<TestDepartmentInfo> departInfo = departmentInfoService.getAllDepartInfo();
        Map<String, String> departMap = departInfo.stream().collect(Collectors.toMap(d -> d.getDepartId(), d -> d.getDepartName()));

        List<EmployeeInfoDto> employeeInfoDtos = employeeInfos.stream().map(info -> {
            EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
            BeanUtils.copyProperties(info, employeeInfoDto);
            employeeInfoDto.setDepartName(departMap.getOrDefault(info.getDepartId(), ""));

            return employeeInfoDto;
        }).collect(Collectors.toList());

        return employeeInfoDtos;


    }
}
