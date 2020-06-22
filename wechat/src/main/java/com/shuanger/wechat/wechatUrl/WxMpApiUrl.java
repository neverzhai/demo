package com.shuanger.wechat.wechatUrl;

import com.shuanger.wechat.wechatConfig.WxMpConfigStorage;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-22 14:37
 * @description:
 */
public interface WxMpApiUrl {


    /**
     * 得到api完整地址.
     *
     * @param config 微信公众号配置
     * @return api地址
     */
    String getUrl(WxMpConfigStorage config);
}
