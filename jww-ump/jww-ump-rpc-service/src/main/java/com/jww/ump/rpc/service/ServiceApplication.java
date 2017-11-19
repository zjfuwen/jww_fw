package com.jww.ump.rpc.service;

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
@ComponentScan(basePackages = {"com.jww.ump.rpc.service.impl", "com.jww.common", "com.jww.ump.dao"})
@ImportResource(value = {"classpath:dubbo/providers.xml"})
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
