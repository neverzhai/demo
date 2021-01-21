package com.shuanger.democommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuanger.democommon.domain.ActivityInfo;
import com.shuanger.democommon.params.CreateActivityRequest;
import com.shuanger.democommon.params.DeleteActivityRequest;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:41
 * @description:
 */
public interface ActivityInfoService extends IService<ActivityInfo> {
    boolean createActivity(CreateActivityRequest request);

    boolean deleteActivity(DeleteActivityRequest request);
}
