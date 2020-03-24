package com.gupao.edu.vip.ebus.entity;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class RemoteAppEvent extends ApplicationEvent {

    /**
     * 事件传输类型HHP,RPC,MQ
     */
    private  String type;

    private String sender;

    /**
     * 应用名称
     */
    private String appName;

    private List<ServiceInstance> serviceInstances;



    public RemoteAppEvent(Object source,
                          String sender,
                          String appName,
                          List<ServiceInstance> serviceInstances) {
        super(source);
        this.sender = sender;
        this.appName = appName;
        this.serviceInstances = serviceInstances;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<ServiceInstance> getServiceInstances() {
        return serviceInstances;
    }

    public void setServiceInstances(List<ServiceInstance> serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
