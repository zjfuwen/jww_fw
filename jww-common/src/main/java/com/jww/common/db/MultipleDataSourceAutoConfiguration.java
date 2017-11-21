package com.jww.common.db;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.ConfigurationCustomizer;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
import com.jww.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanyong
 * @description: 多数据源自动配置
 * @date 2017/11/20 23:15
 */
@Slf4j
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class MultipleDataSourceAutoConfiguration {

    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    private final MybatisPlusProperties properties;

    private final Interceptor[] interceptors;

    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    public MultipleDataSourceAutoConfiguration(MybatisPlusProperties properties,
                                               ObjectProvider<Interceptor[]> interceptorsProvider,
                                               ResourceLoader resourceLoader,
                                               ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                               ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        this.properties = properties;
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }

    /**
     * @return DataSource
     * @description: 主数据源
     * @author wanyong
     * @date 2017/11/21 00:05
     */
    @Primary
    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource masterDataSource() {
        log.info("===========初始化masterDataSource=============");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * @return DataSource
     * @description: 从数据源
     * @author wanyong
     * @date 2017/11/21 00:05
     */
    @Bean("slaverDataSource")
    @ConfigurationProperties(prefix = "datasource.slaver")
    public DataSource slaverDataSource() {
        log.info("===========初始化slaverDataSource=============");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * @return DynamicDataSource
     * @description: 动态数据源
     * @author wanyong
     * @date 2017/11/21 00:56
     */
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaverDataSource") DataSource slaverDataSource) {
        log.info("===========初始化dynamicDataSource=============");
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>(2);
        targetDataSources.put(Constants.DataSourceEnum.MASTER.getName(), masterDataSource);
        targetDataSources.put(Constants.DataSourceEnum.SLAVE.getName(), slaverDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }

    /**
     * @param dynamicDataSource
     * @return SqlSessionFactory
     * @description: sqlsession工厂
     * @author wanyong
     * @date 2017/11/21 00:56
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource)
            throws Exception {
        log.info("================创建sqlSessionFactory====================");
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dynamicDataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        MybatisConfiguration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        factory.setConfiguration(configuration);
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        // TODO 自定义枚举包
        if (StringUtils.hasLength(this.properties.getTypeEnumsPackage())) {
            factory.setTypeEnumsPackage(this.properties.getTypeEnumsPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }
        if (!ObjectUtils.isEmpty(this.properties.getGlobalConfig())) {
            factory.setGlobalConfig(this.properties.getGlobalConfig().convertGlobalConfiguration());
        }
        return factory.getObject();
    }

    /**
     * @param dynamicDataSource
     * @return DataSourceTransactionManager
     * @description: 事务管理
     * @author wanyong
     * @date 2017/11/21 00:57
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) throws Exception {
        log.info("================初始化transactionManager====================");
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
