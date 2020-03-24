package com.gupao.micro.services.spring.cloud.client.event;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class RemoteAppEvent extends ApplicationEvent {

    //http rpc mq   
    private String type;

    //应用名称
    private String appName;

    //应用实例
    private List<ServiceInstance> serviceInstances;

    public RemoteAppEvent(Object source) {
        super(source);
    }
}
