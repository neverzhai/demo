package com.shuanger.javadate.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:31
 * @description: 活动信息domain
 */
@Data
@TableName("activity_info")
public class ActivityInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String activityName;

    private String activityDesc;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Integer deleted;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;
}
