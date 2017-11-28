package com.jww.common.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
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
 * @description: Redis缓存配置类
 * @author shadj
 * @date 2017/11/24 10:19
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * @description:
     *          自定义KEY生成器，格式：
     *                      Cacheable ： cacheNames(0) + 包名 + 类名 +  参数  ,如：keyGenerator.com.jww.common.redis.RedisConfig:param1_param2
     *                      CachePut  ： cacheNames(0) + 包名 + 类名 +  id    ,要求第一个参数为BaseModel
     *                      CacheEvict:  cacheNames(0) + 包名 + 类名 +  id   ,当 allEntries=false 时，需要设置key值，指定主键
     *
     * @return  KeyGenerator
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
                sb.append(target.getClass().getName());
                sb.append(".");
                sb.append(method.getName());
                sb.append(":");
                sb.append(Stream.of(params).map(String::valueOf).collect(Collectors.joining("_")));
                return sb.toString();
            }
        };
    }


    /**
     * @description: 初始化缓存管理类
     * @param redisTemplate
     * @return org.springframework.cache.CacheManager
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
     * @description: RedisTemplate序列化方式设置，并初始化
     * @param: factory
     * @return RedisTemplate
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
