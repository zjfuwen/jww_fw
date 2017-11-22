package com.jww.common.redis.manager;

import com.jww.common.redis.manager.CacheManager;
import com.jww.common.redis.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author shadj
 * @description: Redis缓存辅助类
 * @date 2017/11/21 1:30
 */
@Slf4j
@Component
public final class RedisHelper implements CacheManager {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public RedisHelper(){
        log.info("==============setCacheManager(RedisHelper)================");
        CacheUtil.setCacheManager(this);
    }

    /**
     * @return
     * @description: 放入缓存
     * @param:
     * @author shadj
     * @date 2017/11/21 22:23
     */
    @Override
    public void set(String key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
        // expire(key, EXPIRE);
    }

    /**
     * @return Object
     * @description: 取出缓存，key为String类型
     * @param: key
     * @author shadj
     * @date 2017/11/21 22:14
     */
    @Override
    public Object get(final String key) {
        //expire(key, EXPIRE);
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @description: 根据key模式获取所有的缓存
     * @param: pattern
     * @return Set<Object>
     * @author shadj
     * @date 2017/11/21 22:26
     */
    @Override
    public Set<Object> getAll(String pattern) {
        Set<Object> values = new HashSet<Object>();
        Set<String> keys = redisTemplate.keys(pattern);
        for (String key : keys) {
            values.add(redisTemplate.opsForValue().get(key));
        }
        return values;
    }


    @Override
    public void set(String key, Serializable value, int seconds) {
        redisTemplate.boundValueOps(key).set(value);
        expire(key, seconds);
    }

    @Override
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delAll(String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    @Override
    public String type(String key) {
        return redisTemplate.type(key).getClass().getName();
    }

    /**
     * @description: 在某段时间后失效
     * @param:
     * @return Boolean
     * @author shadj
     * @date 2017/11/21 23:47
     */
    @Override
    public Boolean expire(String key, int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * @description: 在某个时间点失效
     * @param:
     * @return  Boolean
     * @author shadj
     * @date 2017/11/21 23:47
     */
    @Override
    public Boolean expireAt(String key, long unixTime) {
        return redisTemplate.expireAt(key, new Date(unixTime));
    }

    @Override
    public Long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Object getSet(String key, Serializable value) {
        return redisTemplate.opsForValue().getAndSet(key,value);
    }

    @Override
    public boolean lock(String key) {
        //TODO
        return false;
    }

    @Override
    public void unlock(String key) {
        //TODO
    }

    @Override
    public void hset(String key, Serializable field, Serializable value) {
        redisTemplate.boundHashOps(key).put(field,value);
    }

    @Override
    public Object hget(String key, Serializable field) {
        return redisTemplate.boundHashOps(key).get(field);
    }

    @Override
    public void hdel(String key, Serializable field) {
        redisTemplate.boundHashOps(key).delete(field);
    }

    @Override
    public boolean setnx(String key, Serializable value) {
        return false;
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key,1L);
    }

    @Override
    public void setrange(String key, long offset, String value) {
        redisTemplate.boundValueOps(key).set(value, offset);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return redisTemplate.boundValueOps(key).get(startOffset, endOffset);
    }

    @Override
    public void sadd(String key, Serializable value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    @Override
    public Set<?> sall(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    @Override
    public boolean sdel(String key, Serializable value) {
        return redisTemplate.boundSetOps(key).remove(value) == 1;
    }
}
