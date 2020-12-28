package com.shuanger.rocketmqdemo.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuanger.rocketmqdemo.domain.SysUserInfo;
import com.shuanger.rocketmqdemo.domain.SyncUserRequest;


/**
 * <p>
 * 从用户中心同步用户数据 服务类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-11-09
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

    SysUserInfo queryBy(SFunction<SysUserInfo, Object> condition, Object value);

    boolean saveOrUpdateUserInfo(SyncUserRequest request);
}
