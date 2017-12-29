package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.SysDicModel;

/**
 * <p>
 * 字典管理 服务类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
public interface SysDicService extends BaseService<SysDicModel> {

    /**
     * 分页查找所有字典明细
     *
     * @param page
     * @return Page<SysDicModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<SysDicModel> queryListPage(Page<SysDicModel> page);

}
