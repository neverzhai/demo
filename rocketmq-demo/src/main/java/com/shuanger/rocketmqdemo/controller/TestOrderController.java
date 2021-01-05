package com.shuanger.rocketmqdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shuanger.rocketmqdemo.OrderStatus;
import com.shuanger.rocketmqdemo.domain.TestOrder;
import com.shuanger.rocketmqdemo.mq.ExtRocketMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ExtRocketMQTemplate extRocketMQTemplate;



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

        try {
            TransactionSendResult sendResult = extRocketMQTemplate.sendMessageInTransaction(orderTopic, MessageBuilder.withPayload(request).build(),  request);
            log.info("事务结果{}", sendResult.getLocalTransactionState());
        } catch (Exception e) {
            log.error("发送信息异常:{}", e.getMessage());
        }

        return true;
    }

}
