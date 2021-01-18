package com.shuanger.redisdemo.cache;

import cn.hutool.core.util.StrUtil;
import com.shuanger.redisdemo.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-18 16:22
 * @description:
 */
public class RedisCacheServiceImpl implements CacheService {
    private static final Logger log = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisCacheServiceImpl() {
    }

    public Boolean expire(String key, Long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public Long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {
                this.redisTemplate.delete(Arrays.asList(key));
            }
        }

    }

    public Object get(String key) {
        return key == null ? null : this.redisTemplate.opsForValue().get(key);
    }

    public Boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public Boolean set(String key, Object value, Long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }

            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public Long incr(String key, Long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递增因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, delta);
        }
    }

    public Long decr(String key, Long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递减因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, -delta);
        }
    }

    public Object hget(String key, String item) {
        return this.redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    public Boolean hmset(String key, Map<String, Object> map) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public Boolean hmset(String key, Map<String, Object> map, Long time) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public Boolean hset(String key, String item, Object value) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public Boolean hset(String key, String item, Object value, Long time) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public void hdel(String key, Object... item) {
        this.redisTemplate.opsForHash().delete(key, item);
    }

    public Boolean hHasKey(String key, String item) {
        return this.redisTemplate.opsForHash().hasKey(key, item);
    }

    public Double hincr(String key, String item, Double by) {
        return this.redisTemplate.opsForHash().increment(key, item, by);
    }

    public Double hdecr(String key, String item, Double by) {
        return this.redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return this.redisTemplate.opsForSet().members(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public Boolean sHasKey(String key, Object value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public Long sSet(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values);
        } catch (Exception var4) {
            var4.printStackTrace();
            return 0L;
        }
    }

    public Long sSetAndTime(String key, Long time, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().add(key, values);
            if (time > 0L) {
                this.expire(key, time);
            }

            return count;
        } catch (Exception var5) {
            var5.printStackTrace();
            return 0L;
        }
    }

    public Long sGetSetSize(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0L;
        }
    }

    public Long setRemove(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().remove(key, values);
        } catch (Exception var4) {
            var4.printStackTrace();
            return 0L;
        }
    }

    public List<Object> lGet(String key, Long start, Long end) {
        try {
            return this.redisTemplate.opsForList().range(key, start, end);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public Long lGetListSize(String key) {
        try {
            return this.redisTemplate.opsForList().size(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0L;
        }
    }

    public Object lGetIndex(String key, Long index) {
        try {
            return this.redisTemplate.opsForList().index(key, index);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public Boolean lSet(String key, Object value) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public Boolean lSet(String key, Object value, Long time) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public Boolean lSet(String key, List<Object> value) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public Boolean lSet(String key, List<Object> value, Long time) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public Boolean lUpdateIndex(String key, Long index, Object value) {
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public Long lRemove(String key, Long count, Object value) {
        try {
            return this.redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception var5) {
            var5.printStackTrace();
            return 0L;
        }
    }

    public Boolean zadd(String key, Object member, Double score) {
        return this.redisTemplate.opsForZSet().add(key, member, score);
    }

    public Set<Object> zrange(String key, long start, long end) {
        return this.redisTemplate.opsForZSet().range(key, start, end);
    }

    public Long zrank(String key, Object member) {
        return this.redisTemplate.opsForZSet().rank(key, member);
    }

    public Boolean setNX(String key, String value, Long expire) {
        return null == expire ? this.redisTemplate.opsForValue().setIfAbsent(key, value) : this.redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }

    public Object get(String key, Type type) {
        if (StringUtils.isEmpty(key)) {
            return null;
        } else {
            try {
                String cacheStr = (String)this.redisTemplate.opsForValue().get(key);
                if (StringUtils.isEmpty(cacheStr)) {
                    return null;
                } else {
                    Object obj = GsonUtils.fromJson(cacheStr, type);
                    return obj;
                }
            } catch (Exception var5) {
                log.error("加载泛化类型缓存出错 key:{}", key, var5);
                return null;
            }
        }
    }

    public <T> T get(String key, Class<T> klass) {
        if (StringUtils.isEmpty(key)) {
            return null;
        } else {
            try {
                String cacheStr = (String)this.redisTemplate.opsForValue().get(key);
                if (StringUtils.isEmpty(cacheStr)) {
                    return null;
                } else {
                    T obj = GsonUtils.fromJson(cacheStr, klass);
                    return obj;
                }
            } catch (Exception var5) {
                log.error("加载泛化类型缓存出错 key:{}", key, var5);
                return null;
            }
        }
    }

    public Boolean set(String key, int sec, Object obj) {
        if (!StringUtils.isEmpty(key) && obj != null) {
            try {
                this.redisTemplate.opsForValue().set(key, GsonUtils.toJson(obj), (long)sec, TimeUnit.SECONDS);
                return true;
            } catch (Exception var5) {
                log.error("写入，key:{} obj:{}", new Object[]{key, GsonUtils.toJson(obj), var5});
                return false;
            }
        } else {
            return false;
        }
    }

    public void subscribe(String channelName) {
    }

    public void publish(String channelName, String message) {
        this.redisTemplate.convertAndSend(channelName, message);
    }

    public void lpush(String topic, Object msg) {
        try {
            this.redisTemplate.opsForList().leftPush(topic, GsonUtils.toJson(msg));
        } catch (Exception var4) {
            log.error("写入，key:{} obj:{}", new Object[]{topic, GsonUtils.toJson(topic), var4});
        }

    }

    public String rpop(String topic) {
        if (StrUtil.isBlank(topic)) {
            return null;
        } else {
            Object o = null;

            try {
                o = this.redisTemplate.opsForList().rightPop(topic, 1L, TimeUnit.SECONDS);
            } catch (Exception var4) {
                log.error("写入，key:{} obj:{}", new Object[]{topic, GsonUtils.toJson(topic), var4});
            }

            return (String)o;
        }
    }
}
