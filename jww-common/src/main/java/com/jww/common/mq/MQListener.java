package com.jww.common.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 2017/11/21 15:07
 */
@Component
public class MQListener {
    private String text;
//    private int num = 1;

    //监听队列
//    @JmsListener(destination = "${queue-name}")//监听指定消息队列
//    @Async
//    public void receiveQueue(String text) throws Exception {
//        this.text = text;
//        System.out.println(text);
//    }

    //监听【缺省死信队列(Dead Letter Queue)】
//    @JmsListener(destination = "ActiveMQ.DLQ")//监听指定消息队列
//    @Async
//    public void receiveDLQ(String text) throws Exception {
//        this.text = text;
//        System.out.println(text);
//    }

//    //异常测试
//    @JmsListener(destination = "${queue-name}")
//    public void receiveQueue(TextMessage text) throws Exception {
//        System.out.println("重试次数"+num++);
//        System.out.println(Thread.currentThread().getName()+":Consumer收到的报文为:"+text.getText());
//        throw new Exception();
//    }

    public String receive() {
        return text;
    }
}
