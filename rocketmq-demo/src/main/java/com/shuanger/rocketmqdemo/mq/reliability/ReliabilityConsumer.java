package com.shuanger.rocketmqdemo.mq.reliability;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.exception.BusinessException;
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

    @Resource
    private ISysUserInfoService sysUserInfoService;

    @Override
    public void onMessage(SyncUserRequest request) {
        // 通过阅读源码 DefaultRocketMQListenerContainer
        // 可以看到当这个方法抛出异常时, 会通知Broker消息消费失败, 需要重试
        boolean success = sysUserInfoService.saveOrUpdateUserInfo(request);
        if(!success) {
            log.error("用户信息同步失败");
            throw new BusinessException("用户信息同步失败");
        }
    }
}
