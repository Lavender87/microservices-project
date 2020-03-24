package com.gupao.springkafkademo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {

//    @KafkaListener(groupId = "simpleGroup",topics = {"test"})
//    public void listen1(String data) {
//        System.out.println("我收到了消息，呵呵呵呵呵----"+data);
//    }
}
