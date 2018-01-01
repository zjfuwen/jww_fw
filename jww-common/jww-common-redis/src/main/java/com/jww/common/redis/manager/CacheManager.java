package com.jww.common.redis.manager;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Set;

/**
 * 缓存管理接口
 *
 * @author shadj
 * @date 2017/11/21 16:05
 */
public interface CacheManager {

    /**
     * 获取存储在 key(键) 中的 value(数据值) ，如果 key 不存在，则返回空
     *
     * @param key
     * @return Object
     * @author wanyong
     * @date 2017-12-28 00:05
     */
    Object get(final String key);

    /**
     * 根据键模式获取所有匹配的缓存
     *
     * @param pattern
     * @return Set<Object>
     * @author wanyong
     * @date 2017-12-28 00:05
     */
    Set<Object> getAll(final String pattern);

    /**
     * 放入缓存，并设置有效期
     *
     * @param key
     * @param value
     * @param seconds
     * @author shadj
     * @date 2018/1/1 22:23
     */
    void set(final String key, final Serializable value, int seconds);

    /**
     * 设置给定 key 的值。如果 key 已经存储其他值， SET 就覆写旧值，且无视类型
     *
     * @param key
     * @param value
     * @return void
     * @author shadj
     * @date 2018/1/1 22:24
     */
    void set(final String key, final Serializable value);

    /**
     * 检查给定 key 是否存在
     *
     * @param key
     * @return java.lang.Boolean
     * @author shadj
     * @date 2018/1/1 22:25
     */
    Boolean exists(final String key);

    /**
     * 删除已存在的键。不存在的 key 会被忽略
     *
     * @param key
     * @return void
     * @author shadj
     * @date 2018/1/1 22:25
     */
    void del(final String key);

    /**
     * 根据键模式删除所有缓存
     *
     * @param pattern
     * @return void
     * @author shadj
     * @date 2018/1/1 22:25
     */
    void delAll(final String pattern);

    /**
     * 获取指定文件或文件夹的类型
     *
     * @param key
     * @return
     * @author shadj
     * @date 2018/1/1 22:25
     */
    String type(final String key);

    /**
     * 设置 key 的过期时间
     *
     * @param key
     * @param seconds
     * @return
     * @author shadj
     * @date 2018/1/1 22:25
     */
    Boolean expire(final String key, final int seconds);

    /**
     * 以 UNIX 时间戳(unix timestamp)格式设置 key 的过期时间
     *
     * @param key
     * @param unixTime
     * @return java.lang.Boolean
     * @author shadj
     * @date 2018/1/1 22:25
     */
    Boolean expireAt(final String key, final long unixTime);

    /**
     * 以秒为单位返回 key 的剩余过期时间
     *
     * @param key
     * @return java.lang.Long 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
     * @author shadj
     * @date 2017/11/26 21:10
     */
    Long ttl(final String key);

    /**
     * 设置指定 key 的值，并返回 key 的旧值
     *
     * @param key
     * @param value
     * @return java.lang.Object
     * @author shadj
     * @date 2018/1/1 22:25
     */
    Object getSet(final String key, final Serializable value);

    /**
     * 获取指定名称的分布式锁
     *
     * @param lockName 分布式锁名称
     * @return java.lang.String 如果获取锁成功则返回锁键对应值，否则返回null
     * @author shadj
     * @date 2017/11/26 21:23
     */
    String lock(String lockName);

    /**
     * 获取指定名称的分布式锁
     *
     * @param lockName    锁名称
     * @param waitTimeOut 等待获取锁的超时时间（秒）
     * @param lockTimeOut 锁的生存时间（秒）
     * @return java.lang.String 如果获取锁成功则返回锁键对应值，否则返回null
     * @author shadj
     * @date 2017/11/26 21:25
     */
    String lock(String lockName, long waitTimeOut, int lockTimeOut);

    /**
     * 解锁，将判断锁键对应值是否是给定的值，防止误解锁。分布式锁原则之一：每个锁只能被获得锁的客户端删除，或者自动过期释放锁
     *
     * @param lockName  锁名称
     * @param lockValue 锁键对应值
     * @return boolean  解锁成功返回true，否则返回false
     * @author shadj
     * @date 2017/11/26 21:26
     */
    boolean unlock(String lockName, String lockValue);

    /**
     * 为哈希表中的字段赋值 。 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。 如果字段已经存在于哈希表中，旧值将被覆盖
     *
     * @param key
     * @param field
     * @param value
     * @return void
     * @author shadj
     * @date 2018/1/1 22:25
     */
    void hset(String key, Serializable field, Serializable value);

    /**
     * 获取哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return java.lang.Object
     * @author shadj
     * @date 2018/1/1 22:26
     */
    Object hget(String key, Serializable field);

    /**
     * 删除哈希表 key 中的一个或多个指定字段,不存在的字段将被忽略
     *
     * @param key
     * @param field
     * @return void
     * @author shadj
     * @date 2018/1/1 22:26
     */
    void hdel(String key, Serializable field);

    /**
     * 在指定的 key 不存在时,为 key 设置指定的值
     *
     * @param key
     * @param value
     * @return boolean
     * @author shadj
     * @date 2018/1/1 22:26
     */
    boolean setnx(String key, Serializable value);

    /**
     * 将 key 中储存的数字值增一
     *
     * @param key
     * @return java.lang.Long
     * @author shadj
     * @date 2018/1/1 22:28
     */
    Long incr(String key);

    /**
     * 用指定的字符串覆盖给定 key 所储存的字符串值,覆盖的位置从偏移量 offset 开始
     *
     * @param key
     * @param offset
     * @param value
     * @return void
     * @author shadj
     * @date 2018/1/1 22:28
     */
    void setrange(String key, long offset, String value);

    /**
     * 获取存储在键的字符串值的子字符串
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return java.lang.String
     * @author shadj
     * @date 2018/1/1 22:28
     */
    String getrange(String key, long startOffset, long endOffset);

    /**
     * 放入集合缓存一个值
     *
     * @param key
     * @param value
     * @return void
     * @author shadj
     * @date 2018/1/1 22:28
     */
    void sadd(String key, Serializable value);

    /**
     * 获取key对应集合的所有元素
     *
     * @param key
     * @return Set
     * @author shadj
     * @date 2018/1/1 22:28
     */
    Set<?> sall(String key);

    /**
     * 验证集合中的值是否删除
     *
     * @param key
     * @param value
     * @return boolean
     * @author shadj
     * @date 2018/1/1 22:28
     */
    boolean sdel(String key, Serializable value);
}
