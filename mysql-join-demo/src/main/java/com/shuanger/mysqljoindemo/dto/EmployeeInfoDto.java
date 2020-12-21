package com.shuanger.mysqljoindemo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-19 15:04
 * @description:
 */
@Data
public class  EmployeeInfoDto {

    /**
     * 员工所在部门id
     */
    private String departId;

    private String departName;

    /**
     * 员工id
     */
    private String employeeId;

    /**
     * 员工名称
     */
    private String employeeName;

    /**
     * 状态, 1-在职, 2-离职
     */
    private Integer status;

    /**
     * 0：未删除 1：已删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;
}