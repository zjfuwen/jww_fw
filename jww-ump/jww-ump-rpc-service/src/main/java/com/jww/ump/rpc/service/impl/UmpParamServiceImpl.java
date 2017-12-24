package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpParamMapper;
import com.jww.ump.model.UmpParamModel;
import com.jww.ump.model.UmpParamModel;
import com.jww.ump.rpc.api.UmpParamService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 全局参数表 服务实现类
 * </p>
 *
 * @author shadj
 * @since 2017-12-24
 */
@Service("umpParamService")
public class UmpParamServiceImpl extends BaseServiceImpl<UmpParamMapper, UmpParamModel> implements UmpParamService {

    @Override
    public Page<UmpParamModel> queryListPage(Page<UmpParamModel> page) {
        UmpParamModel UmpParamModel = new UmpParamModel();
        UmpParamModel.setIsDel(0);
        EntityWrapper<UmpParamModel> entityWrapper = new EntityWrapper<>(UmpParamModel);
        if (ObjectUtil.isNotNull(page.getCondition())) {
            StringBuilder conditionSql = new StringBuilder();
            Map<String, Object> paramMap = page.getCondition();
            paramMap.forEach((k, v) -> {
                if (StrUtil.isNotBlank(v + "")) {
                    conditionSql.append(k + " like '%" + v + "%' AND ");
                }
            });
            entityWrapper.and(StrUtil.removeSuffix(conditionSql.toString(), "AND "));
        }
        page.setCondition(null);
        return super.selectPage(page, entityWrapper);
    }
}
