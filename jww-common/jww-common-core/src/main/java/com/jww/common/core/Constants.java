package com.jww.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统通用常量
 *
 * @author wanyong
 * @date 2017/11/10 11:20
 */
public final class Constants {

    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * mapper方法中查询语句开头格式，必须以select开头，才会切换数据源
     */
    public static final String MAPPER_METHOD_STARTSWITH_SELECT = "select";

    /**
     * 缓存命名空间
     */
    public static final String CACHE_NAMESPACE = "jww:";

    /**
     * 数据缓存命名空间
     */
    public static final String DATA_CACHE_NAMESPACE = CACHE_NAMESPACE + "data:";

    /**
     * 分布式锁缓存命名空间
     */
    public static final String LOCK_CACHE_NAMESPACE = CACHE_NAMESPACE + "lock:";

    /**
     * TOKEN缓存命名空间
     */
    public static final String TOKEN_CACHE_NAMESPACE = CACHE_NAMESPACE + "token:";
    /**
     * 日志操作类型-新增
     */
    public static final int LOG_OPERATION_TYPE_INSERT = 1;
    /**
     * 日志操作类型-修改
     */
    public static final int LOG_OPERATION_TYPE_MODIFY = 2;
    /**
     * 日志操作类型-删除
     */
    public static final int LOG_OPERATION_TYPE_DELETE = 3;
    /**
     * 日志操作类型-登录
     */
    public static final int LOG_OPERATION_TYPE_LOGIN = 4;
    /**
     * 日志操作类型-查询
     */
    public static final int LOG_OPERATION_TYPE_QUERY = 0;
    /**
     * 日志操作类型-其他
     */
    public static final int LOG_OPERATION_TYPE_UNKONW = 9;

    /**
     * CAPTCHA缓存命名空间
     */
    public static final String CAPTCHA_CACHE_NAMESPACE = CACHE_NAMESPACE + "captcha:";

    /**
     * 缓存键值
     */
    public static final Map<Class<?>, String> CACHE_KEY_MAP = new HashMap<>(5);

    /**
     * 返回码枚举
     */
    public enum ResultCodeEnum {
        /**
         * 成功
         */
        SUCCESS(200, "成功"),
        INTERNAL_SERVER_ERROR(500, "服务器出错"),
        BAD_REQUEST(400, "求参数出错"),
        NO_SUPPORTED_MEDIATYPE(415, "不支持的媒体类型,请使用application/json;charset=UTF-8"),
        LOGIN_FAIL(303, "登录失败"),
        LOGIN_FAIL_ACCOUNT_LOCKED(304, "用户被锁定"),
        LOGIN_FAIL_ACCOUNT_DISABLED(305, "用户未启用"),
        LOGIN_FAIL_ACCOUNT_EXPIRED(306, "用户过期"),
        LOGIN_FAIL_ACCOUNT_UNKNOWN(307, "不存在该用户"),
        LOGIN_FAIL_INCORRECT_CREDENTIALS(308, "密码不正确"),
        LOGIN_FAIL_CAPTCHA_ERROR(309, "验证码错误"),
        UNLOGIN(401, "没有登录"),
        UNAUTHORIZED(403, "没有权限");

        private final int value;
        private final String message;

        ResultCodeEnum(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int value() {
            return this.value;
        }

        public String getMessage() {
            return this.message;
        }

    }

    /**
     * 多数据源枚举
     */
    public enum DataSourceEnum {
        // 主库
        MASTER("masterDataSource", true),
        // 从库
        SLAVE("slaveDataSource", false);

        /**
         * 数据源名称
         */
        private String name;
        /**
         * 是否是默认数据源
         */
        private boolean master;

        DataSourceEnum(String name, boolean master) {
            this.name = name;
            this.master = master;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isMaster() {
            return master;
        }

        public void setMaster(boolean master) {
            this.master = master;
        }

        public String getDefault() {
            String defaultDataSource = "";
            for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
                if (!"".equals(defaultDataSource)) {
                    break;
                }
                if (dataSourceEnum.master) {
                    defaultDataSource = dataSourceEnum.getName();
                }
            }
            return defaultDataSource;
        }

    }

    /**
     * 日志操作类型枚举
     */
    public enum LogOperationTypeEnum {
        /**
         * 查询
         */
        QUERY(0),
        ADD(1),
        MODIFY(2),
        DELETE(3);

        private final int value;

        LogOperationTypeEnum(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

    }
}
