package com.shuanger.rocketmqdemo.mq;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-11-25 15:20
 * @description:
 */
@ExtRocketMQTemplateConfiguration(nameServer = "${rocketmq.name-server}")
public class ExtRocketMQTemplate extends RocketMQTemplate {
}
