package com.jww.common.core.model;


import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseModel;

/**
 * 分页实体类
 *
 * @author wanyong
 * @date 2017/11/23 22:14
 */
public class PageModel<T extends BaseModel> extends Page<T> {

    public PageModel() {
    }

    public PageModel(int current, int size) {
        super(current, size);
    }

    public PageModel(int current, int size, String orderByField) {
        super(current, size);
        super.setOrderByField(orderByField);
    }
}
