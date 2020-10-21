package com.shuanger.wechat.cache;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 18:25
 * @description:
 */
public interface ICacheService {
    boolean setNX(String redisKey, String cached, int expire);
}
