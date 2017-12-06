package com.jww.common.core.base;

import com.baomidou.mybatisplus.service.IService;

/**
 * 业务处理基类接口
 *
 * @author wanyong
 * @date 2017/11/12 11:56
 */
public interface BaseService<T extends BaseModel> extends IService<T> {

    /**
     * 新增
     *
     * @param entity 实体
     * @return T
     * @author wanyong
     * @date 2017/12/6 13:28
     */
    T save(T entity);

    /**
     * 更新
     *
     * @param entity 实体
     * @return T
     * @author wanyong
     * @date 2017/12/6 13:29
     */
    T renew(T entity);

    /**
     * 根据ID查询
     *
     * @param id 实体主键
     * @return T
     * @author wanyong
     * @date 2017/12/6 13:29
     */
    T findById(String id);

}
