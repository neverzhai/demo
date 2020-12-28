package com.shuanger.rocketmqdemo.mq;//package com.jdcity.etown.mq;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-11-25 15:22
 * @description:
 */
@Slf4j
@Service
public class SysUserInfoProducer {

    @Value("${rocketmq.topic.user}")
    private String userTopic;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void produce() {

        SyncUserRequest request = new SyncUserRequest();
        request.setUserId("3ef100c4-227e-11eb-adc1-0242ac120002");
        request.setNickName("shuanger");

        rocketMQTemplate.asyncSend(userTopic,  request, sendCallbackFunc());
    }

    private SendCallback sendCallbackFunc() {
        return new SendCallback() {

            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        };
    }
}
