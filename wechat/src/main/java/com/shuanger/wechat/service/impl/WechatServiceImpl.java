package com.shuanger.wechat.service.impl;

import com.shuanger.wechat.Bo.WechatInfoBo;
import com.shuanger.wechat.VO.WechatInfoVo;
import com.shuanger.wechat.service.WechatService;
import com.shuanger.wechat.wechatConfig.WxConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 11:05
 * @description:
 */
@Slf4j
@Service
public class WechatServiceImpl implements WechatService {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public WechatInfoVo getWechatInfo(String code) {

        String url = WxConstant.WXAPIHOSTURL+"?appid="+WxConstant.APPID+"&secret="+WxConstant.APPSECRET+"&js_code="+code
                +"&grant_type=authorization_code";

        WechatInfoBo forObject = restTemplate.getForObject(url, WechatInfoBo.class);

        return null;
    }
}
