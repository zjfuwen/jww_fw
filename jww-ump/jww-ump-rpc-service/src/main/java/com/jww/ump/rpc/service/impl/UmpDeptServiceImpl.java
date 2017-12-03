package com.jww.ump.rpc.service.impl;

import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpDeptMapper;
import com.jww.ump.model.UmpDept;
import com.jww.ump.rpc.api.UmpDeptService;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/12/1 14:49:30
 */
@Service("umpDeptService")
public class UmpDeptServiceImpl extends BaseServiceImpl<UmpDeptMapper, UmpDept> implements UmpDeptService{

}
