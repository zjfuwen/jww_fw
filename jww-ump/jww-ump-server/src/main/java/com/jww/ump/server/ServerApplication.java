package com.jww.ump.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wanyong
 * @description: 启动类
 * @date 2017/11/17 00:34
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jww.ump.server", "com.jww.common"})
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource(value = {"classpath:dubbo/consumers.xml"})
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
