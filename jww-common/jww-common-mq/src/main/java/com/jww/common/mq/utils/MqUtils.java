package com.jww.common.mq.utils;
import com.jww.common.mq.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * MQ工具类
 *
 * @author RickyWang
 * @date 18/1/1 15:12:46
 */
@Component
public class MqUtils {

    @Autowired
    private MqService mqService;
    private static MqUtils mqUtils;

    @PostConstruct
    public void init(){
        mqUtils = this;
        mqUtils.mqService = this.mqService;
    }

    /**
     * @description: 发送队列
     * @param
     * @return
     * @author Ricky Wang
     * @date 2017/11/21 15:45
     */
    public static void send(String queueName, final Object object){
        mqUtils.mqService.send(queueName, object);
    }

}
