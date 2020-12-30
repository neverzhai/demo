package com.shuanger.rocketmqdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuanger.rocketmqdemo.domain.TestOrder;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 15:14
 * @description:
 */
public interface TestOrderService extends IService<TestOrder> {

    boolean createOrder(TestOrder testOrder);

    TestOrder queryByOrderId(String orderId);
}
