package com.jww.common.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jww.common.core.Constants;
import com.jww.common.core.base.BaseModel;
import com.xiaoleilu.hutool.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shadj
 * @description: Redis缓存配置类
 * @date 2017/11/24 10:19
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * @return KeyGenerator
     * @description: 自定义KEY生成器，格式：
     * Cacheable ： cacheNames(0) + 包名 + 类名 +  参数  ,如：keyGenerator.com.jww.common.redis.RedisConfig:param1_param2
     * CachePut  ： cacheNames(0) + 包名 + 类名 +  id    ,要求第一个参数为BaseModel
     * CacheEvict:  cacheNames(0) + 包名 + 类名 +  id    ,要求第一个参数为BaseModel
     * @author shadj
     * @date 2017/11/21 15:38
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        log.debug("================自定义KEY生成器====================");
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                String cacheName = "";
                String suffix = "";
                if (cacheable != null) {
                    String[] cacheNames = cacheable.value();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                    suffix = Stream.of(params).map(String::valueOf).collect(Collectors.joining("_"));
                } else if (cachePut != null) {
                    String[] cacheNames = cachePut.value();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                    suffix = getIDFromParams(params);
                } else if (cacheEvict != null) {
                    String[] cacheNames = cacheEvict.value();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                    suffix = getIDFromParams(params);
                }
                sb.append(Constants.DATA_CACHE_NAMESPACE).append(target.getClass().getName()).append(":")
                        .append(cacheName).append(":").append(suffix);
                return sb.toString();
            }

            /** 从参数中获取ID */
            private String getIDFromParams(Object[] params) {
                String id = "";
                if (ArrayUtil.isNotEmpty(params)) {
                    //获取第一个参数
                    Object param0 = params[0];
                    //如果第一个参数是BaseModel，则获取ID
                    if (param0 instanceof BaseModel) {
                        BaseModel param0BaseModel = (BaseModel) param0;
                        id = String.valueOf(param0BaseModel.getId());
                    }
                }
                return id;
            }
        };
    }


    /**
     * @param redisTemplate
     * @return org.springframework.cache.CacheManager
     * @description: 初始化缓存管理类
     * @author shadj
     * @date 2017/11/24 14:47
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        return rcm;
    }

    /**
     * @return RedisTemplate
     * @description: RedisTemplate序列化方式设置，并初始化
     * @param: factory
     * @author shadj
     * @date 2017/11/21 15:23
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<String, Serializable>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //KEY 为String方式
        template.setKeySerializer(template.getStringSerializer());
        //VALUE 使用 jackson进行序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
