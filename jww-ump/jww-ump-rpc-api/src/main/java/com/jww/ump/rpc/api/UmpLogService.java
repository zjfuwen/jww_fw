package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpLogModel;

/**
 * 日志业务类
 *
 * @author RickyWang
 * @date 17/12/26 12:41:05
 */
public interface UmpLogService extends BaseService<UmpLogModel> {
    public Page<UmpLogModel> queryListPage(Page<UmpLogModel> page);

    boolean delBatchByIds(Long[] ids);
}
