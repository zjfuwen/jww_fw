package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpDicModel;
import com.jww.ump.model.UmpParamModel;

/**
 * <p>
 * 全局参数表 服务类
 * </p>
 *
 * @author shadj
 * @since 2017-12-24
 */
public interface UmpParamService extends BaseService<UmpParamModel> {

    /**
     * 分页查询参数配置明细
     *
     * @param page
     * @return Page<UmpParamModel>
     * @author shadj
     * @date 2017/12/24 14:45
     */
    Page<UmpParamModel> queryListPage(Page<UmpParamModel> page);

}
