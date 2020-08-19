package com.shuanger.javadate.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:47
 * @description:
 */
@Data
public class CreateActivityRequest {

    @NotBlank(message = "活动名称不能为空")
    private String activityName;

    @NotBlank(message = "活动描述不能为空")
    private String activityDesc;

    @NotNull(message = "活动起始时间不能为空")
    private LocalDate startTime;

    @NotNull(message = "活动结束时间不能为空")
    private LocalDate endTime;
}
