package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpDicModel;

/**
 * <p>
 * 字典管理 服务类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
public interface UmpDicService extends BaseService<UmpDicModel> {

    /**
     * 分页查找所有字典明细
     *
     * @param page
     * @return Page<UmpDicModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<UmpDicModel> queryListPage(Page<UmpDicModel> page);

}
