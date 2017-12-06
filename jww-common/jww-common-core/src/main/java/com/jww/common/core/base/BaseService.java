package com.jww.common.core.base;

import com.baomidou.mybatisplus.service.IService;

/**
 * @author wanyong
 * @description: TODO
 * @date 2017/11/12 11:56
 */
public interface BaseService<T extends BaseModel> extends IService<T> {

    /**
     * 保存
     *
     * @param entity
     * @return T
     * @author wanyong
     * @date 2017-12-06 19:58
     */
    T save(T entity);

    /**
     * 根据主键更新其他字段
     *
     * @param entity
     * @return T
     * @author wanyong
     * @date 2017-12-06 19:58
     */
    T renewById(T entity);

    /**
     * 根据主键查询
     *
     * @param id
     * @return T
     * @author wanyong
     * @date 2017-12-06 19:58
     */
    T findById(Long id);

}
