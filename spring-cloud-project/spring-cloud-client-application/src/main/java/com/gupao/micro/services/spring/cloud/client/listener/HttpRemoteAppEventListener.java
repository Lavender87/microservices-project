package com.gupao.micro.services.spring.cloud.client.listener;

import com.gupao.micro.services.spring.cloud.client.event.RemoteAppEvent;
import org.springframework.context.ApplicationListener;

public class HttpRemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {

    @Override
    public void onApplicationEvent(RemoteAppEvent event) {
        Object sourcr = event.getSource();
    }
}
