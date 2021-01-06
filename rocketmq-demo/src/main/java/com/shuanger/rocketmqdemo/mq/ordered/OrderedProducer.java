package com.shuanger.rocketmqdemo.mq.ordered;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.mq.ExtRocketMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-06 18:00
 * @description:
 */
@Slf4j
@Service
public class OrderedProducer {

    @Value("${demo.rocketmq.topic}")
    private String userTopic;

    @Resource
    private ExtRocketMQTemplate extRocketMQTemplate;

    public void produce() {

        SyncUserRequest request = new SyncUserRequest();
        request.setUserId("b1cab88a-5008-11eb-ae93-0242ac130002");
        request.setNickName("shuanger5");

        // 使用业务上的唯一Id, 设置消息Key, 用于消息查询
        HashMap<String, Object> headers = new HashMap<>();
        String messageKey = request.getUserId() + System.currentTimeMillis();
        headers.put("KEYS", messageKey);

        Message<SyncUserRequest> message = MessageBuilder.createMessage(request, new MessageHeaders(headers));
        log.info("发送消息key: {}", messageKey);

        // userTopic 后面使用:分隔增加tag
        extRocketMQTemplate.asyncSend(userTopic+":reply",  message, sendCallbackFunc());
    }

    // 异步发送时, 要使用回调来判断消息是否发送成功
    private SendCallback sendCallbackFunc() {
        return new SendCallback() {

            @Override
            public void onSuccess(SendResult var1) {
                log.info("async onSucess SendResult= {}", var1);
            }

            @Override
            public void onException(Throwable var1) {
                // 如果发送失败, 则可以根据业务的需要进行重试, 或记入数据库等操作
                log.error("async onException Throwable={}", var1);
            }

        };
    }
}
