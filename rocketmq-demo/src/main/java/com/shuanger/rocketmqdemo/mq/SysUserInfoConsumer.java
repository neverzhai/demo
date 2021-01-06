package com.shuanger.rocketmqdemo.mq;//package com.jdcity.etown.mq;

import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-11-25 10:32
 * @description:
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "user_consumer_user",
        selectorExpression = "user"
)
public class SysUserInfoConsumer implements RocketMQListener<SyncUserRequest> {
// TODO 还有一个ReplyListener是干什么用的, 给Producer返回信息吗
    @Resource
    private ISysUserInfoService sysUserInfoService;


    @Override
    public void onMessage(SyncUserRequest request) {
        // TODO 这里的返回值是void, 消费者消费是否成功如何通知的Borker
        sysUserInfoService.saveOrUpdateUserInfo(request);
    }
}
