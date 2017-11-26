package com.jww.common.mq.propties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/11/24 15:16:50
 */
@Component
@ConfigurationProperties(prefix = "spring.activemq")
public class MQPropties {

    @Getter@Setter private String defaultQueueName;

    @Getter@Setter private Integer queuePrefetch;
}
