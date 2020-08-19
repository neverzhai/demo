package com.shuanger.javadate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.javadate.dao.ActivityInfoMapper;
import com.shuanger.javadate.domain.ActivityInfo;
import com.shuanger.javadate.service.ActivityInfoService;
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
}
