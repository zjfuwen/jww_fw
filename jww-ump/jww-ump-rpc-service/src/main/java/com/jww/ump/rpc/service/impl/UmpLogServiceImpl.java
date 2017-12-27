package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.ump.dao.mapper.UmpLogMapper;
import com.jww.ump.model.UmpLogModel;
import com.jww.ump.rpc.api.UmpLogService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author RickyWang
 * @since 2017-12-26
 */
@Service("umpLogService")
@Slf4j
public class UmpLogServiceImpl extends BaseServiceImpl<UmpLogMapper, UmpLogModel> implements UmpLogService {

    @Autowired
    private UmpLogMapper umpLogMapper;

    @Override
    public Page<UmpLogModel> queryListPage(Page<UmpLogModel> page) {
        log.info("UmpLogServiceImpl->queryListPage->page:" + page.toString());
        log.info("UmpLogServiceImpl->queryListPage->page->condition:" + JSON.toJSONString(page.getCondition()));

        UmpLogModel umpLogModel = new UmpLogModel();
        umpLogModel.setIsDel(0);
        EntityWrapper<UmpLogModel> entityWrapper = new EntityWrapper<>(umpLogModel);
        if (ObjectUtil.isNotNull(page.getCondition())) {
            StringBuilder conditionSql = new StringBuilder();
            Map<String, Object> paramMap = page.getCondition();
            paramMap.forEach((k, v) -> {
                if (StrUtil.isNotBlank(v + "")) {
                    conditionSql.append(k + " like '%" + v + "%' OR ");
                }
            });
            entityWrapper.and(StrUtil.removeSuffix(conditionSql.toString(), "OR "));
        }
        page.setCondition(null);
        return super.selectPage(page, entityWrapper);
    }

    @Override
    public boolean delBatchByIds(Long[] ids) {
        List<UmpLogModel> list = new ArrayList<>();
        for(Long id : ids){
            UmpLogModel umpLogModel = new UmpLogModel();
            umpLogModel.setId(id);
            umpLogModel.setIsDel(1);
            list.add(umpLogModel);
        }
        return super.updateBatchById(list);
    }

}
