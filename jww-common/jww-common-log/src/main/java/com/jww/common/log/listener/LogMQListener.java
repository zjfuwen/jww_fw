package com.jww.common.log.listener;

import com.alibaba.fastjson.JSONObject;
import com.jww.common.constant.Constants;
import com.jww.common.constant.MQConstants;
import com.jww.common.log.model.SysLogModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/11/24 16:48:35
 */
@Component
@Slf4j
public class LogMQListener {
//    //监听队列
//    @JmsListener(destination = MQConstants.DEFAULT_LOG_QUEUE_NAME)//监听默认日志队列
//    @Async
//    public void receiveQueue(String msg){
//        SysLogModel  sysLogModel = null;
//        try{
//            sysLogModel = JSONObject.parseObject(msg, SysLogModel.class);
////            sysLogModel = (SysLogModel)JSONObject.parse(msg);
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        log.info("sysLogModel: {}", sysLogModel);
//    }
}
