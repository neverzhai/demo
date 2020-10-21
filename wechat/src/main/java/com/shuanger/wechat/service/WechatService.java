package com.shuanger.wechat.service;

import com.shuanger.wechat.VO.WechatInfoVo;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 11:05
 * @description: 调用微信获取用的信息服务
 */
public interface WechatService {
    WechatInfoVo getWechatInfo(String code);
}
