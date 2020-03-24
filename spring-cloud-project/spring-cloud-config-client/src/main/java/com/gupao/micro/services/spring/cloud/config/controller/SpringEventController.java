package com.gupao.micro.services.spring.cloud.config.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringEventController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


//    @GetMapping("/send/event")
//    public String sendEvent(@RequestParam String message) {
//        publisher.publishEvent(message);
//        return "Sent";
//    }

    @GetMapping("/send/event")
    public String sendEvent(@RequestParam String message){
        publisher.publishEvent(message);
        return "sent";
    }

    @EventListener
    public void onMessage(PayloadApplicationEvent event){
        System.out.println("接受事件：" + event.getPayload());
    }
}
