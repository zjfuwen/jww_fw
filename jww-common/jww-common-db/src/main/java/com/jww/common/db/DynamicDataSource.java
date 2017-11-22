package com.jww.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wanyong
 * @description: 动态数据源（数据源切换）
 * @date 2017/11/17 13:33
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 取得当前使用哪个数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
