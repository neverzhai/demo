package com.shuanger.rocketmqdemo.mq.transaction;

import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 16:16
 * @description:
 */
//@Configuration
public class TransactionProducerConfig {


//    @Value("${rocketmq.producer.group}")
//    private String orderProducer;
//
//    @Resource
//    private TransactionListenerImpl transactionListener;
//
////    @Resource
////    private ExecutorService executorService;
//
//    @Bean
//    public TransactionMQProducer createTransactionMQProducer() {
//
//        TransactionMQProducer producer = new TransactionMQProducer(orderProducer);
//        producer.setTransactionListener(transactionListener);
////        producer.setExecutorService(executorService);
//
//        return producer;
//
//    }
}
