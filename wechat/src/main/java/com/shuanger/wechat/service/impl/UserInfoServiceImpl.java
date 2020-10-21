package com.shuanger.wechat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.wechat.dao.UserInfoDao;
import com.shuanger.wechat.entity.UserInfo;
import com.shuanger.wechat.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 11:10
 * @description:
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
}
