package com.shuanger.rocketmqdemo.mq;//package com.jdcity.etown.mq;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-11-25 15:22
 * @description:
 */
@Slf4j
@Service
public class SysUserInfoProducer {

    @Value("${demo-rocketmq.topic}")
    private String userTopic;

    @Resource
    private ExtRocketMQTemplate extRocketMQTemplate;

    public void produce() {

        SyncUserRequest request = new SyncUserRequest();
        request.setUserId("3ef100c4-227e-11eb-adc1-0242ac120002");
        request.setNickName("shuanger3");

        HashMap<String, Object> headers = new HashMap<>();
        String messageKey = request.getUserId() + System.currentTimeMillis();
        headers.put("KEYS", messageKey);

        Message<SyncUserRequest> message = MessageBuilder.createMessage(request, new MessageHeaders(headers));
        log.info("发送消息key: {}", messageKey);

        // userTopic 后面使用:分隔增加tag
        extRocketMQTemplate.asyncSend(userTopic+":user",  message, sendCallbackFunc());
    }

    private SendCallback sendCallbackFunc() {
        return new SendCallback() {

            @Override
            public void onSuccess(SendResult var1) {
                log.info("async onSucess SendResult= {}", var1);
            }

            @Override
            public void onException(Throwable var1) {
                log.error("async onException Throwable={}", var1);
            }

        };
    }
}
