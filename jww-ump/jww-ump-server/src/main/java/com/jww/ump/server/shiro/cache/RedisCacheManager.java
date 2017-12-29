package com.jww.ump.server.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Shiro缓存管理器（Redis实现）
 *
 * @author shadj
 * @date 2017/12/29 18:21
 */
@Component
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return redisCache;
    }
}
