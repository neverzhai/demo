package com.shuanger.democommon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.democommon.dao.ActivityInfoMapper;
import com.shuanger.democommon.domain.ActivityInfo;
import com.shuanger.democommon.params.CreateActivityRequest;
import com.shuanger.democommon.params.DeleteActivityRequest;
import com.shuanger.democommon.service.ActivityInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:42
 * @description:
 */
@Slf4j
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoService {

    public boolean createActivity(CreateActivityRequest request) {
        log.info("创建活动, 活动名称: {}", request.getActivityName());

        return true;
    }

    public boolean deleteActivity(DeleteActivityRequest request) {
        log.info("删除活动, 活动id: {}", request.getActivityId());
        return true;
    }
}
