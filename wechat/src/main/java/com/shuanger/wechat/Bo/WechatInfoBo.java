package com.shuanger.wechat.Bo;

import lombok.Data;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 11:18
 * @description:
 */
@Data
public class WechatInfoBo {

    private String openid;

    private String session_key;

    private String unionid;

    private Integer errcode;

    private String errmsg;

}
