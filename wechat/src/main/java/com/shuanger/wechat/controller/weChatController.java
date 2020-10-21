package com.shuanger.wechat.controller;

import com.shuanger.wechat.params.GetWechatInfoRequest;
import com.shuanger.wechat.response.BusinessCode;
import com.shuanger.wechat.response.ResponseResult;
import com.shuanger.wechat.service.UserInfoService;
import com.shuanger.wechat.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-22 10:45
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class weChatController {

    @Resource
    private WechatService wechatService;

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/getAccessToken")
    public Object getAccessToken() {

        return "accesssToken";
    }

    /**
     * 使用openId + mobile登录
     * 如果想在登录时获得用户的昵称和头像怎么操作
     */
    @RequestMapping("/login")
    public ResponseResult login() {

        // 登录及注册
        // 返回登录成功的token
        return ResponseResult.create(BusinessCode.SUCCESS);
    }


    /**
     * 使用mobile + verify code登录
     */
    @RequestMapping("/loginByMobile")
    public ResponseResult loginByMobile() {

        // 登录及注册
        // 返回登录成功的token
        return ResponseResult.create(BusinessCode.SUCCESS);
    }

    /**
     * 获取微信openId, sessionKey
     */
    @RequestMapping("/getWechatInfo")
    public ResponseResult getWechatInfo(@RequestBody @Validated GetWechatInfoRequest request) {

        wechatService.getWechatInfo(request.getCode());
        return ResponseResult.create(BusinessCode.SUCCESS);
    }

    /**
     * 获取微信中用的的一些信息, 如昵称 , 头像, 语言等
     */
    @RequestMapping("/getWechatUserInfo")
    public ResponseResult getWechatUserInfo() {

        return ResponseResult.create(BusinessCode.SUCCESS);
    }

    /**
     * 获取授权的手机号, 其实就是一个解密的过程, 从缓存中取出加密的数据进行解密
     */
    @RequestMapping("/wechatMobile")
    public ResponseResult getWechatMobile() {

        return ResponseResult.create(BusinessCode.SUCCESS);
    }


}
