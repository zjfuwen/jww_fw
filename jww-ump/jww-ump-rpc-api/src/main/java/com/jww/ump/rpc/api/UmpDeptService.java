package com.jww.ump.rpc.api;

import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpDept;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/12/1 11:26:33
 */
public interface UmpDeptService extends BaseService<UmpDept>{
    Page<UmpDept> queryListPage(Page<UmpDept> page);
    boolean delBatchByIds(List<Long> ids);
}
