package com.shuanger.redisdemo.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-02-24 20:37
 * @description:
 */
@Slf4j
@Component
public class GuavaBloomFilterFactory {

    enum FilterKey {
        APP_USER,
        GOODS_ID
    }

    private static final int DEFAULT_SIZE = 1000000;

    private ConcurrentHashMap<FilterKey, BloomFilter<String>> filterFactory = new ConcurrentHashMap<>(FilterKey.values().length, 1);

    public BloomFilter<String> getFilter(FilterKey key) {
        if (filterFactory.containsKey(key)) {
            return filterFactory.get(key);
        }
        synchronized (key) {
            if (filterFactory.containsKey(key)) {
                return filterFactory.get(key);
            }
            BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), DEFAULT_SIZE);
            filterFactory.put(key, filter);
            return filter;
        }
    }
}
