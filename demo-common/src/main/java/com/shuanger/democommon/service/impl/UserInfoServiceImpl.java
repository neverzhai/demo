package com.shuanger.democommon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.democommon.dao.UserInfoMapper;
import com.shuanger.democommon.domain.UserInfo;
import com.shuanger.democommon.params.CreateUserRequest;
import com.shuanger.democommon.service.UserInfoService;
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

    public boolean createUser(CreateUserRequest request) {
        log.info("创建用户: username: {}", request.getUsername());
        return false;
    }
}
