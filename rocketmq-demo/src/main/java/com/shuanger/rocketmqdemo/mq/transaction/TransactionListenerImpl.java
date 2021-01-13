package com.shuanger.rocketmqdemo.mq.transaction;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.shuanger.rocketmqdemo.domain.TestOrder;
import com.shuanger.rocketmqdemo.service.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 16:20
 * @description:
 */
@Slf4j
@Component
@RocketMQTransactionListener(rocketMQTemplateBeanName = "extRocketMQTemplate")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Resource
    private TestOrderService testOrderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(org.springframework.messaging.Message message, Object o) {
        log.info("执行本地事务, 消息:{}", JSONObject.toJSONString(message));

        // 执行本地业务逻辑, 如果本地事务执行成功, 则通知Broker可以提交消息让Consumer进行消费
        TestOrder testOrder = (TestOrder) o;
        try {
            boolean success = testOrderService.createOrder(testOrder);
            return success ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.UNKNOWN;

        } catch (Exception e) {
            log.info("本地任务执行异常: {}", e.getMessage());

            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(org.springframework.messaging.Message message) {
        log.info("check local transaction ");
        // 提供事务执行状态的回查方法，提供给broker回调
        // 正常情况下不会调用到
        String orderId = (String) message.getPayload();
        TestOrder testOrder = testOrderService.queryByOrderId(orderId);

        if (ObjectUtil.isNotNull(testOrder)) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
