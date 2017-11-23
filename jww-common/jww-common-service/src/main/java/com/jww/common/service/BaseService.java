package com.jww.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.jww.common.core.model.BaseModel;

/**
 * @author wanyong
 * @description: TODO
 * @date 2017/11/12 11:56
 */
public interface BaseService<T extends BaseModel> extends IService<T> {

    T save(T entity);

    T renew(T entity);

    T findById(String id);

}
