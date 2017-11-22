package com.jww.common.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jww.common.db.BaseModel;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author wanyong
 * @description: TODO
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

    @Override
    @Cacheable(value = "findById", key = "#id")
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
