package com.jww.common.base;

import com.baomidou.mybatisplus.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author wanyong
 * @description: TODO
 * @date 2017/11/12 11:56
 */
public interface BaseService<T extends BaseModel> extends IService<T> {

    public T save(T entity);

    public T renew(T entity);

    public T findById(String id);

}
