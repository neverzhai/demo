package com.shuanger.rocketmqdemo.mq.reliability;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-04 10:00
 * @description: 消息可靠性消费者
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "user_consumer_reliability",
        selectorExpression = "reliability"
)
public class ReliabilityConsumer implements RocketMQListener<SyncUserRequest>  {

    // 如何通知Broker成功消费了消息

    // TODO 还有一个ReplyListener是干什么用的, 给Producer返回信息吗
    @Resource
    private ISysUserInfoService sysUserInfoService;


    @Override
    public void onMessage(SyncUserRequest request) {
        // TODO 这里的返回值是void, 消费者消费是否成功如何通知的Borker
        sysUserInfoService.saveOrUpdateUserInfo(request);
    }
}
