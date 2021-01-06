package com.shuanger.rocketmqdemo.mq.replayListener;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-06 18:03
 * @description:
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "user_consumer_reply",
        selectorExpression = "reply"
)
public class ReplyConsumer implements RocketMQReplyListener<SyncUserRequest, Boolean> {
    // 当Producer使用同步方式发送时, 使用RocketMQReplyListener, 用于给Producer返回信息
    @Resource
    private ISysUserInfoService sysUserInfoService;

    @Override
    public Boolean  onMessage(SyncUserRequest request) {
        return sysUserInfoService.saveOrUpdateUserInfo(request);
    }
}
