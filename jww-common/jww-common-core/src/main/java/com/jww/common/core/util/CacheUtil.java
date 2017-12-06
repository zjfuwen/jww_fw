package com.jww.common.core.util;

import com.jww.common.core.Constants;
import com.jww.common.core.manager.CacheManager;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.cache.annotation.CacheConfig;

/**
 * 缓存工具类
 *
 * @author shadj
 * @date 2017/11/21 16:35
 */
public class CacheUtil {

    private static CacheManager cacheManager;

    public static void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }

    public static CacheManager getCache() {
        return cacheManager;
    }

    /**
     * 获取缓存键值
     */
    public static String getCacheKey(Object id, Class<?> cls) {
        String cacheName = getCacheKey(cls);
        return new StringBuilder(Constants.DATA_CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }

    /**
     * 获取锁键值
     */
    public static String getLockKey(Object id, Class<?> cls) {
        String cacheName = getCacheKey(cls);
        return new StringBuilder(Constants.LOCK_CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }

    /**
     * @return
     */
    public static String getCacheKey(Class<?> cls) {
        String cacheName = Constants.CACHE_KEY_MAP.get(cls);
        if (StrUtil.isBlank(cacheName)) {
            CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
            if (cacheConfig != null && ArrayUtil.isNotEmpty(cacheConfig.cacheNames())) {
                cacheName = cacheConfig.cacheNames()[0];
            } else {
                cacheName = cls.getName();
            }
            Constants.CACHE_KEY_MAP.put(cls, cacheName);
        }
        return cacheName;
    }
}