package com.gupao.edu.vip.ebus.controller;

import com.gupao.edu.vip.ebus.entity.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class RemoteAppEventSenderController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentAppName;

    @GetMapping("/send/remote/event")
    public String sendEvent(@RequestParam String message){
        return "send";
    }

    @PostMapping("/send/remote/event/{appName}")
    public String sendAppCluster(@PathVariable String appName, @RequestBody Map data){
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data,appName,true);
        publisher.publishEvent(remoteAppEvent);
        return "ok";
    }




    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
