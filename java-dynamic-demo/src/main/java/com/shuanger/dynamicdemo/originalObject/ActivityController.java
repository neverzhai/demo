package com.shuanger.dynamicdemo.originalObject;

import com.shuanger.democommon.params.CreateActivityRequest;
import com.shuanger.democommon.params.DeleteActivityRequest;
import com.shuanger.democommon.service.ActivityInfoService;
import com.shuanger.dynamicdemo.aop.OperationAudit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-20 22:27
 * @description:
 */
@RequestMapping("/activity")
@RestController
public class ActivityController {

    @Resource
    private ActivityInfoService activityInfoService;


    @OperationAudit(value = "create a activity")
    @PostMapping ("/create")
    public boolean createActivity(@RequestBody CreateActivityRequest request) {
        return activityInfoService.createActivity(request);
    }

    @OperationAudit(value = "delete a activity")
    @PostMapping("/delete")
    public boolean deleteActivity(@RequestBody DeleteActivityRequest request) {
        return activityInfoService.deleteActivity(request);
    }
}