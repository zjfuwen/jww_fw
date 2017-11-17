package com.jww.ump.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wanyong
 * @description: 启动类
 * @date 2017/11/17 00:34
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jww.ump.server", "com.jww.common"})
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
