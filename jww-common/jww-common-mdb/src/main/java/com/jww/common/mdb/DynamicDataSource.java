package com.jww.common.mdb;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（数据源切换）
 *
 * @author wanyong
 * @date 2017/11/17 13:33
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 取得当前使用哪个数据源
     *
     * @return Object
     * @author wanyong
     * @date 2017-12-11 19:47
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
