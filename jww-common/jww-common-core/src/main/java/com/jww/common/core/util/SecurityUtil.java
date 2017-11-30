package com.jww.common.core.util;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.lang.Base64;

/**
 * 使用一段文字进行描述
 *
 * @author waner
 * @create 2017-11-30
 **/
public class SecurityUtil {

    /**
     * 加密密码
     *
     * @param password
     * @return String
     */
    public static String encryptPassword(String password) {
        return encryptMd5(encryptSHA(password));
    }

    public static String encryptSHA(String data) {
        return Base64.encode(SecureUtil.sha1(data));
    }

    public static String encryptMd5(String data) {
        return Base64.encode(SecureUtil.md5(data));
    }
}
