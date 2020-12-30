package com.shuanger.rocketmqdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.rocketmqdemo.dao.TestShoppingCarMapper;
import com.shuanger.rocketmqdemo.domain.TestShoppingCar;
import com.shuanger.rocketmqdemo.service.TestShoppingCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 15:47
 * @description:
 */
@Slf4j
@Service
public class TestShoppingCarServiceImpl extends ServiceImpl<TestShoppingCarMapper, TestShoppingCar> implements TestShoppingCarService {
}
