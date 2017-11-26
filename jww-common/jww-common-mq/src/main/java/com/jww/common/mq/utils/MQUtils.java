package com.jww.common.mq.utils;
import com.jww.common.mq.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Title: MQ工具类
 * @Description:
 * @Author: Ricky Wang
 * @Date: 2017/11/21 14:15
 */
@Component
public class MQUtils {

    @Autowired
    private MQService mqService;
    private static MQUtils mqUtils;

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
