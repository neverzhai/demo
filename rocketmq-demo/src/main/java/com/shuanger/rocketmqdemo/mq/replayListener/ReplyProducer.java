package com.shuanger.rocketmqdemo.mq.replayListener;

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
 * @date: 2021-01-06 18:03
 * @description:
 */
@Slf4j
@Service
public class ReplyProducer {

    @Value("${demo.rocketmq.topic}")
    private String userTopic;

    @Resource
    private ExtRocketMQTemplate extRocketMQTemplate;

    public void produce() {

        SyncUserRequest request = new SyncUserRequest();
        request.setUserId("3ef100c4-227e-11eb-adc1-0242ac120002");
        request.setNickName("shuanger3");

        // 使用业务上的唯一Id, 设置消息Key, 用于消息查询
        HashMap<String, Object> headers = new HashMap<>();
        String messageKey = request.getUserId() + System.currentTimeMillis();
        headers.put("KEYS", messageKey);

        Message<SyncUserRequest> message = MessageBuilder.createMessage(request, new MessageHeaders(headers));
        log.info("发送消息key: {}", messageKey);
        log.info("发送消息key: {}", messageKey);

        // 接收Reply, RocketMQ Server 需要升级到4.7.1, 否则会报如下错误: CODE: 10007  DESC: create reply message fail, requestMessage error, property[CLUSTER] is null.
        // 使用同步方式, 需要consumer端返回消息, 使用RocketMQReplyListener
        try {
            Boolean success = extRocketMQTemplate.sendAndReceive(userTopic + ":reply", message, Boolean.class);
            log.info("消息发送成功");
        } catch (Exception e) {
            // 保证消息可靠性, 需要catch 异常
            log.info("消息发送失败");
        }
    }

}
