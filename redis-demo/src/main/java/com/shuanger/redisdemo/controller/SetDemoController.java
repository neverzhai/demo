package com.shuanger.redisdemo.controller;

import com.shuanger.redisdemo.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-06 17:53
 * @description:
 */
@Slf4j
@RequestMapping("/set")
@RestController
public class SetDemoController {

    @Resource
    private RedisTemplate redisTemplate;


    @RequestMapping("/test1")
    public Boolean setString() {

        Long add = redisTemplate.opsForSet().add("sorted1", "user1");
        Long user2 = redisTemplate.opsForSet().add("sorted1", "user2");

        Long size = redisTemplate.opsForSet().size("sorted1");
        log.info("size:{}", size);

        redisTemplate.expire("sorted1", 60, TimeUnit.SECONDS);
//        Iterator iterator = range.iterator();
//        while (iterator.hasNext()) {
//            log.info(iterator.next());

//        long timeMillis = System.currentTimeMillis();
//        Instant.ofEpochMilli(timeMillis).atZone(ZoneOffset.ofHours(8)).toLocalDate();
//        }
        return true;
    }
}
