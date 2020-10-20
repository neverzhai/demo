package com.shuanger.wechat.params;

import lombok.Data;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-20 15:26
 * @description:
 */
@Data
public class UserLoginRequest {

    private String openId;

    private String mobile;
}
