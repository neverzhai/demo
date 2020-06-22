package com.shuanger.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-22 10:45
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class weChatController {

    @RequestMapping("/getAccessToken")
    public Object getAccessToken() {

        return "accesssToken";
    }
}
