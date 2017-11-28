package com.jww.ump.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wanyong
 * @description: 启动类
 * @date 2017/11/17 00:34
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jww.ump.server", "com.jww.common.web", "com.jww.common.log.web"})
@ImportResource(value = {"classpath:dubbo/consumers.xml"})
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
