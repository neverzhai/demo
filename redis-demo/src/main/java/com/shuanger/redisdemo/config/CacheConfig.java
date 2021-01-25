package com.shuanger.redisdemo.config;

import com.google.gson.Gson;
import com.shuanger.redisdemo.utils.GsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-18 16:26
 * @description:
 */
@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
//        genericToStringSerializer.setConversionService(new ConversionService() {
//            public boolean canConvert(Class<?> aClass, Class<?> aClass1) {
//                return true;
//            }
//
//            public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
//                return true;
//            }
//
//            public <T> T convert(Object o, Class<T> aClass) {
//                String s = GsonUtils.toJson(o);
//                return GsonUtils.fromJson(s, aClass);
//            }
//
//            public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
//                return GsonUtils.toJson(o);
//            }
//        });
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        JdkSerializationRedisSerializer serializationRedisSerializer = new JdkSerializationRedisSerializer();

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());

        template.setHashKeySerializer(StringRedisSerializer.UTF_8);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

}
