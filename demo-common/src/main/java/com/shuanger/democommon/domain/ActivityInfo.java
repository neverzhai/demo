package com.shuanger.democommon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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

    private Date startTime;

    private Date endTime;

    private Integer deleted;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;
}
