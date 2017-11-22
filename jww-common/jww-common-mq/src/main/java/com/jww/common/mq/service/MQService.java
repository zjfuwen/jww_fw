package com.jww.common.mq.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service("mqService")
@Slf4j
public class MQService {
    @Autowired() // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsMessagingTemplate;
    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void send(String queueName, final Object object){
        send(new ActiveMQQueue(queueName), object);
    }

    public void send(final Object object){
        send(jmsMessagingTemplate.getJmsTemplate().getDefaultDestination(), object);
    }

    public void send(Destination destination, final Object object){
        String message = object instanceof String ? object.toString() :  JSONObject.toJSONString(object);
        try{
            jmsMessagingTemplate.convertAndSend(destination, message);
        }catch (MessagingException e){
            log.error(e.toString());
        }
    }

    public Object receive() {
        Message message = jmsMessagingTemplate.receive(jmsMessagingTemplate.getJmsTemplate().getDefaultDestinationName());
        return message.getPayload();
    }

    public Object receive(String queueName) {
        Message message = jmsMessagingTemplate.receive(queueName);
        return message.getPayload();
    }
}
