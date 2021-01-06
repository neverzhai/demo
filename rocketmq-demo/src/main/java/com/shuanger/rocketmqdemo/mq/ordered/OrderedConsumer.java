package com.shuanger.rocketmqdemo.mq.ordered;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-06 18:00
 * @description:
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "user_consumer_ordered",
        selectorExpression = "ordered"
)
public class OrderedConsumer implements RocketMQListener<SyncUserRequest> {

    @Resource
    private ISysUserInfoService sysUserInfoService;


    @Override
    public void onMessage(SyncUserRequest request) {
        sysUserInfoService.saveOrUpdateUserInfo(request);
    }
}
