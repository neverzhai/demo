package com.shuanger.rocketmqdemo.mq.reliability;

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
 * @date: 2021-01-04 09:59
 * @description: 消息可靠性生产者
 */
@Slf4j
@Service
public class ReliabilityProducer {

    @Value("${demo.rocketmq.topic}")
    private String userTopic;

    @Resource
    private ExtRocketMQTemplate extRocketMQTemplate;

    public void produce() {

        SyncUserRequest request = new SyncUserRequest();
        request.setUserId("3c62ea3e-4fec-11eb-ae93-0242ac130002");
        request.setNickName("shuanger4");

        HashMap<String, Object> headers = new HashMap<>();
        String messageKey = request.getUserId() + System.currentTimeMillis();
        headers.put("KEYS", messageKey);

        Message<SyncUserRequest> message = MessageBuilder.createMessage(request, new MessageHeaders(headers));
        log.info("发送消息key: {}", messageKey);

        extRocketMQTemplate.asyncSend(userTopic+":reliability",  message, sendCallbackFunc());
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
