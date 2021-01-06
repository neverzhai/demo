package com.shuanger.rocketmqdemo.controller;

import com.shuanger.rocketmqdemo.OrderStatus;
import com.shuanger.rocketmqdemo.domain.TestOrder;
import com.shuanger.rocketmqdemo.mq.ExtRocketMQTemplate;
import com.shuanger.rocketmqdemo.mq.reliability.ReliabilityProducer;
import com.shuanger.rocketmqdemo.mq.replayListener.ReplyProducer;
import com.shuanger.rocketmqdemo.mq.simple.SysUserInfoProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;

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

    @Resource
    private SysUserInfoProducer sysUserInfoProducer;

    @Resource
    private ReliabilityProducer reliabilityProducer;

    @Resource
    private ReplyProducer replyProducer;

    @Value("${demo.rocketmq.topic}")
    private String orderTopic;

    // 预下单接口-- 状态是待支付
    @GetMapping("/pre")
    public Boolean createOrder() {

        TestOrder request = new TestOrder();
        request.setUserId("3ef100c4-227e-11eb-adc1-0242ac120002");
        request.setOrderId("6dfdedb6-4f54-11eb-ae93-0242ac130002");
        request.setGoodsId("6dfde96a-4f54-11eb-ae93-0242ac130002");
        request.setPaymentAmount(new BigDecimal(20));
        request.setOrderStatus(OrderStatus.UNPAYED.getCode());

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("KEYS", request.getOrderId());
        MessageHeaders messageHeaders = new MessageHeaders(headers);

        try {

            Message<TestOrder> message = MessageBuilder.createMessage(request, messageHeaders);
            TransactionSendResult sendResult = extRocketMQTemplate.sendMessageInTransaction(orderTopic +":order",  message,  request);
            log.info("事务结果{}", sendResult.getLocalTransactionState());
        } catch (Exception e) {
            log.error("发送信息异常:{}", e.getMessage());
        }

        return true;
    }

    @GetMapping("/test")
    public Boolean testSentSys()  {
        sysUserInfoProducer.produce();

        return true;
    }

    @GetMapping("/reliability")
    public Boolean testReliability()  {
        reliabilityProducer.produce();

        return true;
    }

    @GetMapping("/reply")
    public Boolean testReply()  {
        replyProducer.produce();

        return true;
    }

}
