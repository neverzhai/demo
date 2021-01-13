package com.shuanger.snowworkiddemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuchangcun
 * @desc
 * @date 2020/10/29
 */
@Component
public class IdGenerator {

    @Autowired
    private SnowFlakeIDGenerator snowFlakeIDGenerator;
//
//    @Autowired
//    private ICacheService cacheService;

    private static final long BASE_INCREASE_FACTOR = 100000;

    /**
     * 生成会员卡号
     *
     * @return
     */
//    public String generateMemberNo() {
//        LocalDate now = LocalDate.now();
//        String yearMonth = String.format("%s%s", now.getYear(), now.getMonth().getValue());
//        long number = cacheService.incrMonthly(yearMonth);
//        String memberNo = String.format("E%s%s", yearMonth, BASE_INCREASE_FACTOR + number);
//
//        return memberNo;
//    }

    /**
     * 生成订单ID
     *
     * @return
     */
    public long generateOrderId() {
        return snowFlakeIDGenerator.generate();
    }

    /**
     * 生成核销ID
     *
     * @return
     */
    public long generateCheckOutId() {
        return snowFlakeIDGenerator.generate();
    }

}
