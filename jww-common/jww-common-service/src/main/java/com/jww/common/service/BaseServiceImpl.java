package com.jww.common.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jww.common.core.model.BaseModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * 业务处理实现基类
 *
 * @author wanyong
 * @date 2017/11/19 20:36
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel>
        extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {

    @Override
    public T renew(T entity) {
        if (super.update(entity, null)) {
            return entity;
        }
        return null;
    }

    @Cacheable(value = "findById")
    @Override
    public T findById(String id) {
        return super.selectById(id);
    }

    @Override
    public T save(T entity) {
        if (super.insert(entity)) {
            return entity;
        }
        return null;
    }
}
