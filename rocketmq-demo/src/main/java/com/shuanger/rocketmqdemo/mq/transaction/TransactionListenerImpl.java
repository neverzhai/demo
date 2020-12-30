package com.shuanger.rocketmqdemo.mq.transaction;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.shuanger.rocketmqdemo.domain.TestOrder;
import com.shuanger.rocketmqdemo.service.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 16:20
 * @description:
 */
@Slf4j
@Service
public class TransactionListenerImpl implements TransactionListener {

    @Resource
    private TestOrderService testOrderService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("执行本地事务, 消息:{}", JSONObject.toJSONString(message));

        // 执行本地业务逻辑, 如果本地事务执行成功, 则通知Broker可以提交消息让Consumer进行消费

        TestOrder testOrder = (TestOrder) o;
        try {
            boolean success = testOrderService.createOrder(testOrder);
            return success ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.UNKNOW;

        } catch (Exception e) {

            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("check local transaction ");
        // 提供事务执行状态的回查方法，提供给broker回调
        // 正常情况下不会调用到
        String orderId = messageExt.getUserProperty("orderId");
        TestOrder testOrder = testOrderService.queryByOrderId(orderId);

        if (ObjectUtil.isNotNull(testOrder)) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
