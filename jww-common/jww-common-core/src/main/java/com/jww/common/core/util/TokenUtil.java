package com.jww.common.core.util;

import com.alibaba.fastjson.JSON;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.TokenModel;

/**
 * 令牌工具类
 *
 * @author wanyong
 * @date 2017/11/23 22:14
 */
public class TokenUtil {
    public static void setTokenInfo(String token, String value) {
        try {
            TokenModel tokenInfo = new TokenModel();
            tokenInfo.setTime(System.currentTimeMillis());
            tokenInfo.setValue(value);
            // CacheUtil.getCache().hset(Constants.TOKEN_KEY, token, JSON.toJSONString(tokenInfo));
        } catch (Exception e) {
            throw new BusinessException("保存token失败，错误信息：", e);
        }
    }

    public static void delToken(String token) {
        try {
            // CacheUtil.getCache().hdel(Constants.TOKEN_KEY, token);
        } catch (Exception e) {
            throw new BusinessException("删除token失败，错误信息：", e);
        }
    }

    public static TokenModel getTokenInfo(String token) {
        // String value = (String) CacheUtil.getCache().hget(Constants.TOKEN_KEY, token);
        String value = "";
        return JSON.parseObject(value, TokenModel.class);
    }
}
