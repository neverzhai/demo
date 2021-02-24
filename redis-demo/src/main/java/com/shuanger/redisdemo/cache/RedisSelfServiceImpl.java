package com.shuanger.redisdemo.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-25 14:18
 * @description: redis 不同数据类型的操作练习,
 * Redis 提供的数据结构: String List, HasMap,  Set  Sorted Set
 */
@Service
public class RedisSelfServiceImpl {

    // 对于key和value 都是 String的类型, 推荐使用StringRedisTemplate
//    @Resource
//    StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    // String 类型
    public Boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // List
    public Boolean lSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);

        return true;
    }

    // HashMap
    public Boolean hSet(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return true;
    }

    public boolean bloom() {

//        redisTemplate.opsForValue().setBit()
        return true;
    }




}
