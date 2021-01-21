package com.shuanger.dynamicdemo.originalObject;


import com.shuanger.democommon.params.CreateActivityRequest;
import com.shuanger.democommon.params.DeleteActivityRequest;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-21 11:21
 * @description: 活动管理服务接口
 */
public interface IActivityController {

    boolean createActivity(CreateActivityRequest request);

    boolean deleteActivity(DeleteActivityRequest request);
}
