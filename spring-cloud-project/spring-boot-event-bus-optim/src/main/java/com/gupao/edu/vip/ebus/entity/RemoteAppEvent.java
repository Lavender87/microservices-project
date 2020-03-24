package com.gupao.edu.vip.ebus.entity;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class RemoteAppEvent extends ApplicationEvent {

    /**
     * 事件传输类型HHP,RPC,MQ
     */
    private  String type;
    /**
     * 应用名称
     */
    private String appName;

    private boolean isCluster;




    public RemoteAppEvent(Object source,
                          String appName,
                          boolean isCluster) {
        super(source);
        this.isCluster = isCluster;
        this.appName = appName;
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

    public boolean isCluster() {
        return isCluster;
    }

    public void setCluster(boolean cluster) {
        isCluster = cluster;
    }
}
