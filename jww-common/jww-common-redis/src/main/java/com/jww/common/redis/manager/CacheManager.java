package com.jww.common.redis.manager;

import java.io.Serializable;
import java.util.Set;

/**
 * @description: 缓存管理接口
 * @author shadj
 * @date 2017/11/21 16:05
 */
public interface CacheManager {

    Object get(final String key);

    Set<Object> getAll(final String pattern);

    void set(final String key, final Serializable value, int seconds);

    void set(final String key, final Serializable value);

    Boolean exists(final String key);

    void del(final String key);

    void delAll(final String pattern);

    String type(final String key);

    Boolean expire(final String key, final int seconds);

    Boolean expireAt(final String key, final long unixTime);

    /**
     * @description:
     *             获取 key 的剩余过期时间
     * @param key
     * @return java.lang.Long 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
     * @author shadj
     * @date 2017/11/26 21:10
     */
    Long ttl(final String key);

    Object getSet(final String key, final Serializable value);

    /**
     * @description:
     *             获取指定名称的分布式锁
     * @param lockName 分布式锁名称
     * @return java.lang.String 如果获取锁成功则返回锁键对应值，否则返回null
     * @author shadj
     * @date 2017/11/26 21:23
     */
    String lock(String lockName);

    /**
     * @description:
     *             获取指定名称的分布式锁
     * @param lockName  锁名称
     * @param waitTimeOut 等待获取锁的超时时间（秒）
     * @param lockTimeOut   锁的生存时间（秒）
     * @return java.lang.String 如果获取锁成功则返回锁键对应值，否则返回null
     * @author shadj
     * @date 2017/11/26 21:25
     */
    String lock(String lockName,long waitTimeOut,int lockTimeOut);

    /**
     * @description:
     *             解锁，将判断锁键对应值是否是给定的值，防止误解锁。分布式锁原则之一：每个锁只能被获得锁的客户端删除，或者自动过期释放锁
     * @param lockName  锁名称
     * @param lockValue 锁键对应值
     * @return boolean  解锁成功返回true，否则返回false
     * @author shadj
     * @date 2017/11/26 21:26
     */
    boolean unlock(String lockName,String lockValue);

    void hset(String key, Serializable field, Serializable value);

    Object hget(String key, Serializable field);

    void hdel(String key, Serializable field);

    boolean setnx(String key, Serializable value);

    Long incr(String key);

    void setrange(String key, long offset, String value);

    String getrange(String key, long startOffset, long endOffset);

    void sadd(String key, Serializable value);

    Set<?> sall(String key);

    boolean sdel(String key, Serializable value);
}
