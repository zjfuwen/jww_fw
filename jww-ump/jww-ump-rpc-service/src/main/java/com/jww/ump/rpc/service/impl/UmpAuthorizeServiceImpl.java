package com.jww.ump.rpc.service.impl;

import com.jww.ump.dao.mapper.UmpAuthorizeMapper;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.rpc.api.UmpAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用一段文字进行描述
 *
 * @author waner
 * @create 2017-11-29
 **/
@Service("umpAuthorizeService")
public class UmpAuthorizeServiceImpl implements UmpAuthorizeService {

    @Autowired
    private UmpAuthorizeMapper umpAuthorizeMapper;

    @Override
    public List<String> findPermissionByUserId(Long userId) {
        return umpAuthorizeMapper.selectPermissionByUserId(userId);
    }
}
