package com.jww.common.core.base;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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
    T add(T entity);

    /**
     * 更新
     *
     * @param entity 实体
     * @return T
     * @author wanyong
     * @date 2017/12/6 13:29
     */
    T modifyById(T entity);

    /**
     * 根据ID查询
     *
     * @param id 实体主键
     * @return T
     * @author wanyong
     * @date 2017/12/6 13:29
     */
    T queryById(Long id);


    /**
     * 根据ID集合批量删除
     *
     * @param entity 实体对象
     * @param ids    ID集合
     * @return boolean
     * @author wanyong
     * @date 2017-12-27 11:45
     */
    boolean delBatchByIds(T entity, List<Long> ids);

}
