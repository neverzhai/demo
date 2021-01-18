package com.shuanger.redisdemo.cache;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-18 16:21
 * @description:
 */
public interface CacheService {
    Boolean expire(String var1, Long var2);

    Long getExpire(String var1);

    Boolean hasKey(String var1);

    void del(String... var1);

    Object get(String var1);

    Boolean set(String var1, Object var2);

    Boolean set(String var1, Object var2, Long var3);

    Long incr(String var1, Long var2);

    Long decr(String var1, Long var2);

    Object hget(String var1, String var2);

    Map<Object, Object> hmget(String var1);

    Boolean hmset(String var1, Map<String, Object> var2);

    Boolean hmset(String var1, Map<String, Object> var2, Long var3);

    Boolean hset(String var1, String var2, Object var3);

    Boolean hset(String var1, String var2, Object var3, Long var4);

    void hdel(String var1, Object... var2);

    Boolean hHasKey(String var1, String var2);

    Double hincr(String var1, String var2, Double var3);

    Double hdecr(String var1, String var2, Double var3);

    Set<Object> sGet(String var1);

    Boolean sHasKey(String var1, Object var2);

    Long sSet(String var1, Object... var2);

    Long sSetAndTime(String var1, Long var2, Object... var3);

    Long sGetSetSize(String var1);

    Long setRemove(String var1, Object... var2);

    List<Object> lGet(String var1, Long var2, Long var3);

    Long lGetListSize(String var1);

    Object lGetIndex(String var1, Long var2);

    Boolean lSet(String var1, Object var2);

    Boolean lSet(String var1, Object var2, Long var3);

    Boolean lSet(String var1, List<Object> var2);

    Boolean lSet(String var1, List<Object> var2, Long var3);

    Boolean lUpdateIndex(String var1, Long var2, Object var3);

    Long lRemove(String var1, Long var2, Object var3);

    Boolean zadd(String var1, Object var2, Double var3);

    Set<Object> zrange(String var1, long var2, long var4);

    Long zrank(String var1, Object var2);

    Boolean setNX(String var1, String var2, Long var3);

    Object get(String var1, Type var2);

    <T> T get(String var1, Class<T> var2);

    Boolean set(String var1, int var2, Object var3);

    void subscribe(String var1);

    void publish(String var1, String var2);

    void lpush(String var1, Object var2);

    String rpop(String var1);
}

