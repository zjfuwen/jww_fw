package com.jww.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author wanyong
 * @description: TODO
 * @date 2017/11/12 11:56
 */
@Slf4j
public abstract class BaseService<T extends BaseModel> {

    @Autowired
    protected BaseMapper<T> mapper;

    /**
     * @description: 更新实体（如果实体ID为空，则新建该实体）
     * @param record
     * @return T
     * @author wanyong
     * @date 2017/11/14 17:37
     */
    public T renew(T record) {
        record.setUpdateTime(new Date());
        if (record.getId() == null) {
            record.setCreateTime(new Date());
            mapper.insert(record);
            //TODO 缓存实体待实现
        }else{
            //TODO 发布式锁待实现
            mapper.updateById(record);
        }
        return record;
    }

}
