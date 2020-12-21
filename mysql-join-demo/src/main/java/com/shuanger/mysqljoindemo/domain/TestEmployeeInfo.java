package com.shuanger.mysqljoindemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestEmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工所在部门id
     */
    private String departId;

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
