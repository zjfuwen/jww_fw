package com.jww.ump.rpc.service;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * @author wanyong
 * @description: 启动类
 * @date 2017/11/17 00:34
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.jww.ump.rpc.service.impl", "com.jww.common.db"})
@ImportResource(value = {"classpath:dubbo/providers.xml"})
@MapperScan(basePackages = {"com.jww.ump.dao.mapper"})
public class ServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ServiceApplication.class, args);
        /*ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(ServiceApplication.class)
                .web(false)
                .run(args);*/

        log.info("项目启动!");
        System.in.read();
    }
}
