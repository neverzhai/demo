package com.shuanger.redisdemo.controller;

import cn.hutool.json.JSONObject;
import com.shuanger.redisdemo.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-02-24 14:57
 * @description: 使用redis sorted set 进行比赛排名, 或评论的最新列表
 */
@Slf4j
@RequestMapping("/sortedSet")
@RestController
public class SortedSetDemoController {

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/test1")
    public Boolean setString() {

        Boolean user1 = redisTemplate.opsForZSet().add("sorted1", "user1", 1.0);
        Boolean user11 = redisTemplate.opsForZSet().add("sorted1", "user1", 3.0);
        Boolean user2 = redisTemplate.opsForZSet().add("sorted1", "user2", 2.0);

        redisTemplate.opsForZSet().incrementScore("sorted1", "user1", 2.0);

        Long size = redisTemplate.opsForZSet().size("sorted1");
        log.info("size:{}", size);
        redisTemplate.opsForZSet().removeRange("sorted1", size -1, size);
        Set<String> range = redisTemplate.opsForZSet().reverseRange("sorted1", 0, 5);

        redisTemplate.expire("sorted1", 60, TimeUnit.SECONDS);
        log.info(GsonUtils.toJson(range));
//        Iterator iterator = range.iterator();
//        while (iterator.hasNext()) {
//            log.info(iterator.next());

        long timeMillis = System.currentTimeMillis();
        Instant.ofEpochMilli(timeMillis).atZone(ZoneOffset.ofHours(8)).toLocalDate();
//        }
        return true;
    }
}
