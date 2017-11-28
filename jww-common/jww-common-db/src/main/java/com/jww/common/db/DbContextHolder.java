package com.jww.common.db;

import com.jww.common.core.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanyong
 * @description: 数据源DbContextHolder
 * @date 2017/11/17 13:31
 */
public class DbContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源
     *
     * @param dataSourceEnum
     */
    public static void setDbType(Constants.DataSourceEnum dataSourceEnum) {
        contextHolder.set(dataSourceEnum.getName());
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDbType() {
        String dataSource = contextHolder.get();
        // 如果没有指定数据源，使用默认数据源
        if (StringUtils.isEmpty(dataSource)) {
            DbContextHolder.setDbType(Constants.DataSourceEnum.MASTER);
        }
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
