package com.jww.ump.dao.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.jww.ump.dao.mapper")
public class MybatisPlusConfig {

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
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }
}
