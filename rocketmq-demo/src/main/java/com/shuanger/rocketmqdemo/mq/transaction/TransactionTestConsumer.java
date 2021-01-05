package com.shuanger.rocketmqdemo.mq.transaction;
//
import com.shuanger.rocketmqdemo.domain.TestOrder;
import com.shuanger.rocketmqdemo.service.TestShoppingCarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 15:50
 * @description:
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${demo.rocketmq.topic}",
        consumerGroup = "${rocketmq.consumer.group}",
        selectorExpression = "order"
)
public class TransactionTestConsumer implements RocketMQListener<TestOrder> {

    @Resource
    private TestShoppingCarService shoppingCarService;

    @Override
    public void onMessage(TestOrder message) {
        // 收到预下单成功的消息后, 将商品从用户的购物车中删除
        log.info("consumer on message : {}", message);
        boolean success = shoppingCarService.removeGoodsForUser(message.getGoodsId(), message.getUserId());
        log.info("购物车删除状态:{} ", success);
        
    }
}
