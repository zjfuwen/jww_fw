package com.jww.ump.server;

import com.jww.common.db.MultipleDataSourceAutoConfiguration;
import com.jww.common.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wanyong
 * @description: 启动类
 * @date 2017/11/17 00:34
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SessionAutoConfiguration.class})
@ComponentScan(basePackages = {"com.jww.ump.server", "com.jww.common.web", "com.jww.common.annotation", "com.jww.common.aspect"})
@ImportResource(value = {"classpath:dubbo/consumers.xml"})
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
