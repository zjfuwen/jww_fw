package com.jww.common.redis.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @description: HttpSession缓存配置
 * @author shadj
 * @date 2017/11/24 10:19
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*30)
public class SessionConfig {

}
