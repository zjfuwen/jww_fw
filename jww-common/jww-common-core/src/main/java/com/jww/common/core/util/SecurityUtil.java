package com.jww.common.core.util;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.lang.Base64;

/**
 * 加解密工具类
 *
 * @author wanyong
 * @date 2017/11/12 11:54
 */
public class SecurityUtil {

    /**
     * 加密密码
     *
     * @param password
     * @return String
     * @author wanyong
     * @date 2017-12-12 00:20
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

    public static void main(String[] args) {
        String password = "123456";
        System.out.println("SecurityUtil->encryptPassword->password:" + SecurityUtil.encryptPassword(password));
    }
}
