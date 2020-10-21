package com.shuanger.wechat.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 11:13
 * @description:
 */
@Data
public class GetWechatInfoRequest {

    @NotBlank(message = "临时授权码不能为空")
    private String code;
}
