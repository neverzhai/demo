package com.shuanger.mysqljoindemo.controller;


import com.shuanger.mysqljoindemo.dto.EmployeeInfoDto;
import com.shuanger.mysqljoindemo.service.ITestEmployeeInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
@RestController
@RequestMapping("/mysqljoindemo/employee")
public class TestEmployeeInfoController {

    @Resource
    private ITestEmployeeInfoService employeeInfoService;

    @GetMapping("getWihtJoin")
    public ResponseEntity<List<EmployeeInfoDto>> getEmployeeInfoWithJoin() {
        List<EmployeeInfoDto> employeeInfoWithJoin = employeeInfoService.getEmployeeInfoWithJoin();

        return ResponseEntity.ok(employeeInfoWithJoin);
    }

    @GetMapping("getWithoutJoin")
    public ResponseEntity<List<EmployeeInfoDto>> getEmployeeInfoWithoutJoin() {
        List<EmployeeInfoDto> employeeWithoutJoin = employeeInfoService.getEmployeeWithoutJoin();

        return ResponseEntity.ok(employeeWithoutJoin);
    }

}

