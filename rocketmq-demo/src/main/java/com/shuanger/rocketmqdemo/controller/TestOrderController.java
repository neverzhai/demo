package com.shuanger.rocketmqdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shuanger.rocketmqdemo.OrderStatus;
import com.shuanger.rocketmqdemo.domain.TestOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 17:03
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("order")
public class TestOrderController {

    @Resource
    private TransactionMQProducer transactionMQProducer;

    @Value("${rocketmq.topic.order}")
    private String orderTopic;

    // 预下单接口-- 状态是待支付
    @GetMapping("/pre")
    public Boolean createOrder() {

        TestOrder request = new TestOrder();
        request.setUserId("3ef100c4-227e-11eb-adc1-0242ac120002");
        request.setOrderId("2d552868-4a74-11eb-b378-0242ac130002");
        request.setPaymentAmount(new BigDecimal(20));
        request.setOrderStatus(OrderStatus.UNPAYED.getCode());

        Message message = new Message(orderTopic, JSONObject.toJSONBytes(request));
        try {
            TransactionSendResult sendResult = transactionMQProducer.sendMessageInTransaction(message, request);
            log.info("事务结果{}", sendResult.getLocalTransactionState());

        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return true;
    }


    //
}
