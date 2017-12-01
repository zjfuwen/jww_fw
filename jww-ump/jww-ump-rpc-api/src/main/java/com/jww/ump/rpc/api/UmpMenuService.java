package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpMenuModel;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
public interface UmpMenuService extends BaseService<UmpMenuModel> {

    public List<UmpMenuModel> findList();
}
