package com.shuanger.redisdemo.controller;

import com.shuanger.redisdemo.utils.BloomFilterHelper;
import com.shuanger.redisdemo.utils.RedisBloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-02-24 15:56
 * @description: redis布隆过滤器
 */
@Slf4j
@RequestMapping("/bloom")
@RestController
public class BloomFilterController {

    @Autowired
    RedisBloomFilter redisBloomFilter;

    @Autowired
    private BloomFilterHelper bloomFilterHelper;

    @ResponseBody
    @RequestMapping("/add")
    public String addBloomFilter(@RequestParam("orderNum") String orderNum) {


        try {
            redisBloomFilter.addByBloomFilter(bloomFilterHelper,"bloom",orderNum);
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }

        return "添加成功";
    }

    @ResponseBody
    @RequestMapping("/check")
    public boolean checkBloomFilter(@RequestParam ("orderNum") String orderNum) {

        boolean b = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, "bloom", orderNum);



        return b;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};

//        for (int[] items = ints;;) {
//            System.out.println(items[1]);
//
//        }

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.entrySet();

        new ReentrantLock();

        new ReentrantReadWriteLock();

            new ConcurrentHashMap<>();
    }


//    new HashMap<>()





}
