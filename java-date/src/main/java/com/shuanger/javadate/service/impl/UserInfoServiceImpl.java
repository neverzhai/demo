package com.shuanger.javadate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.javadate.dao.UserInfoMapper;
import com.shuanger.javadate.domain.UserInfo;
import com.shuanger.javadate.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:45
 * @description:
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
