package com.shuanger.rocketmqdemo.mq.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 15:50
 * @description:
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${rocketmq.topic.order}",
        consumerGroup = "${rocketmq.consumer.group}"
)
public class TransactionTestConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("consumer on message : {}", message);

    }
}
