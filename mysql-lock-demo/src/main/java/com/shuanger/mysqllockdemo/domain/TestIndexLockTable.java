package com.shuanger.mysqllockdemo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 测试index对锁的影响
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestIndexLockTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 自定义id
     */
    private String customId;

    /**
     * 名称
     */
    private String name;

    /**
     * 用户当前的等级收集的金果数量
     */
    private Long currentFruitCount;

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
