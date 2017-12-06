package com.jww.common.core.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.util.CacheUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;

/**
 * 业务处理基类实现
 *
 * @author wanyong
 * @date 2017/11/19 20:36
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel>
        extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {

    @Override
    @CacheEvict(value = "DATA")
    public T renewById(T entity) {
        T resultEntity = null;
        entity.setUpdateTime(new Date());
        String lockKey = CacheUtil.getLockKey(entity.getId(), getClass());
        String lockValue = CacheUtil.getCache().lock(lockKey);
        if (StrUtil.isNotBlank(lockValue)) {
            if (super.updateById(entity)) {
                resultEntity = entity;
            }
            CacheUtil.getCache().unlock(lockKey, lockValue);
        } else {
            throw new BusinessException("数据不一致,请刷新页面重新编辑!");
        }
        return resultEntity;
    }

    @Override
    @Cacheable(value = "DATA")
    public T findById(Long id) {
        return super.selectById(id);
    }

    @Override
    @CachePut(value = "DATA")
    public T save(T entity) {
        entity.setCreateTime(new Date());
        if (super.insert(entity)) {
            return entity;
        }
        return null;
    }
}
