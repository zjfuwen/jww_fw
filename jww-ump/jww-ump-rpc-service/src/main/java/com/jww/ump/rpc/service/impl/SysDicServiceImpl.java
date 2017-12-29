package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.SysDicMapper;
import com.jww.ump.model.SysDicModel;
import com.jww.ump.rpc.api.SysDicService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 字典管理 服务实现类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
@Service("sysDicService")
public class SysDicServiceImpl extends BaseServiceImpl<SysDicMapper, SysDicModel> implements SysDicService {

    @Override
    public Page<SysDicModel> queryListPage(Page<SysDicModel> page) {
        SysDicModel sysDicModel = new SysDicModel();
        sysDicModel.setIsDel(0);
        EntityWrapper<SysDicModel> entityWrapper = new EntityWrapper<>(sysDicModel);
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
