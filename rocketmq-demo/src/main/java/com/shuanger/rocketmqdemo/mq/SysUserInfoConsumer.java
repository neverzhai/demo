package com.shuanger.rocketmqdemo.mq;//package com.jdcity.etown.mq;

import com.alibaba.fastjson.JSONObject;
import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.service.ISysUserInfoService;
import com.shuanger.rocketmqdemo.utils.AesUtilsCbc;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-11-25 10:32
 * @description:
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${rocketmq.topic.user}",
        consumerGroup = "user_consumer",
        accessKey = "RocketMQ",
        secretKey = "12345678"
)
public class SysUserInfoConsumer implements RocketMQListener<String> {

    @Resource
    private ISysUserInfoService sysUserInfoService;

    @Value("${rocketmq.topic.cbcAesKey}")
    private String cbcKey;

    @Override
    public void onMessage(String request) {
        String aeSdecrypt = AesUtilsCbc.AESdecrypt(request, cbcKey);
        log.info("开始同步用户信息，解密后信息是:", aeSdecrypt);

        SyncUserRequest userRequest = JSONObject.parseObject(aeSdecrypt, SyncUserRequest.class);

        sysUserInfoService.saveOrUpdateUserInfo(userRequest);
    }
}
