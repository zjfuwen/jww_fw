package com.jww.common.core;

/**
 * @author wanyong
 * @description: 系统通用常量
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

    public enum ResultCodeEnum {
        SUCCESS(200, "成功"),
        INTERNAL_SERVER_ERROR(500, "服务器出错"),
        LOGIN_FAIL(303, "登录失败"),
        LOGIN_FAIL_ACCOUNT_LOCKED(304, "用户被锁定"),
        LOGIN_FAIL_ACCOUNT_DISABLED(305, "用户未启用"),
        LOGIN_FAIL_ACCOUNT_EXPIRED(306, "用户过期"),
        LOGIN_FAIL_ACCOUNT_UNKNOWN(307, "不存在该用户"),
        LOGIN_FAIL_INCORRECT_CREDENTIALS(308, "密码不正确"),
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

    public enum DataSourceEnum {
        // 主库
        MASTER("masterDataSource", true),
        // 从库
        SLAVE("slaveDataSource", false);

        // 数据源名称
        private String name;
        // 是否是默认数据源
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
}
