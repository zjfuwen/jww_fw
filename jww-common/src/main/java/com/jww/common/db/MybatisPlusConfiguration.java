package com.jww.common.db;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.jww.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanyong
 * @description: 自定义mybatisplus配置
 * @date 2017/11/20 21:46
 */
@Slf4j
@Configuration
public class MybatisPlusConfiguration {

    /**
     * @return PerformanceInterceptor
     * @description: SQL执行效率插件【生产环境可以关闭】
     * @author wanyong
     * @date 2017/11/19 11:59
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * @return PaginationInterceptor
     * @description: 分页插件
     * @author wanyong
     * @date 2017/11/19 11:59
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("================初始化paginationInterceptor====================");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }
}
