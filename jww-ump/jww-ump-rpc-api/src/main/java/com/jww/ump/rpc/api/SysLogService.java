package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.SysLogModel;

/**
 * 日志业务类
 *
 * @author RickyWang
 * @date 17/12/26 12:41:05
 */
public interface SysLogService extends BaseService<SysLogModel> {
    public Page<SysLogModel> queryListPage(Page<SysLogModel> page);

    boolean delBatchByIds(Long[] ids);
}
