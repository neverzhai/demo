package com.shuanger.wechat.wechatConfig;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-22 14:38
 * @description:
 */
public interface WxMpConfigStorage {


    /**
     * 得到微信接口地址域名部分的自定义设置信息.
     */
    WxMpHostConfig getHostConfig();
}
