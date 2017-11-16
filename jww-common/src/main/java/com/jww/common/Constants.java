package com.jww.common;

/**
 * @author wanyong
 * @description: 系统通用常量
 * @date 2017/11/10 11:20
 */
public final class Constants {

    /** 当前用户 */
    public static final String CURRENT_USER = "CURRENT_USER";

    public enum ResultCodeEnum {
        SUCCESS(200, "成功"),
        INTERNAL_SERVER_ERROR(500, "服务器出错");

        private final int value;
        private final String message;

        private ResultCodeEnum(int value, String message) {
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
