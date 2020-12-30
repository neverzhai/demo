package com.shuanger.rocketmqdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.rocketmqdemo.dao.TestOrderMapper;
import com.shuanger.rocketmqdemo.domain.TestOrder;
import com.shuanger.rocketmqdemo.service.TestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 15:46
 * @description:
 */
@Slf4j
@Service
public class TestOrderServiceImpl extends ServiceImpl<TestOrderMapper, TestOrder> implements TestOrderService {


    @Override
    public boolean createOrder(TestOrder testOrder) {

        return save(testOrder);
    }

    @Override
    public TestOrder queryByOrderId(String orderId) {
        LambdaQueryWrapper<TestOrder> queryWrapper = new QueryWrapper<TestOrder>().lambda()
                .eq(TestOrder::getOrderId, orderId)
                .eq(TestOrder::getDeleted, false);

        return getOne(queryWrapper);
    }
}
