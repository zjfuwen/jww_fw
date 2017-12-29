package com.jww.ump.server.shiro.cache;

import com.jww.common.core.Constants;
import com.jww.common.redis.util.CacheUtil;
import com.xiaoleilu.hutool.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * Shiro缓存之Redis实现类
 *
 * @author shadj
 * @date 2017/12/29 18:28
 */
@Slf4j
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    @Override
    public V get(K key) throws CacheException {
        log.info("获取缓存，key:{}", getCacheKey(key));
        V value = (V) CacheUtil.getCache().get(getCacheKey(key));
        return value;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        log.info("放入缓存，key:{},value:{}", getCacheKey(key), value);
        CacheUtil.getCache().set(getCacheKey(key), (Serializable) value, 30 * 60);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        log.info("清除缓存，key:{}", getCacheKey(key));
        V value = (V) get(key);
        CacheUtil.getCache().del(getCacheKey(key));
        return value;
    }

    @Override
    public void clear() throws CacheException {
        log.info("清空缓存，pattern:{}", getKeyPrefix() + "*");
        CacheUtil.getCache().delAll(getKeyPrefix() + "*");
    }

    @Override
    public int size() {
        return CacheUtil.getCache().getAll(getKeyPrefix() + "*").size();
    }

    @Override
    public Set<K> keys() {
        Set<Object> keys = CacheUtil.getCache().getAll(getKeyPrefix() + "*");
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptySet();
        }
        Set<K> newKeys = new HashSet<K>();
        for (Object key : keys) {
            newKeys.add((K) key);
        }
        return newKeys;
    }

    @Override
    public Collection<V> values() {
        Set<Object> keys = CacheUtil.getCache().getAll(getKeyPrefix() + "*");
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptyList();
        }
        List<V> values = new ArrayList<V>(keys.size());
        for (Object key : keys) {
            V value = get((K) key);
            if (value != null) {
                values.add(value);
            }
        }
        return Collections.unmodifiableList(values);
    }

    /**
     * 获取缓存KEY
     */

    private String getCacheKey(K key) {
        String cacheName = CacheUtil.getCacheKey(this.getClass());
        return new StringBuilder(getKeyPrefix()).append(cacheName).append(":").append(key).toString();
    }

    /**
     * 获取缓存key前缀
     */
    private String getKeyPrefix() {
        return Constants.SHIRO_CACHE_NAMESPACE;
    }
}
