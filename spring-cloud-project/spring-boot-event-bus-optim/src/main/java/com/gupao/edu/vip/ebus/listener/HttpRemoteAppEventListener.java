package com.gupao.edu.vip.ebus.listener;

import com.gupao.edu.vip.ebus.entity.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import javax.naming.ldap.HasControls;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRemoteAppEventListener implements SmartApplicationListener {

    private RestTemplate restTemplate = new RestTemplate();

//    @Value("${spring.application.name}")
    private String currentAppName;

//    @Autowired
    private DiscoveryClient discoveryClient;

    public void onApplicationEvent(RemoteAppEvent event) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(event.getAppName());
        for(ServiceInstance s:serviceInstances){
            String urlRoot =s.isSecure()?
                    "https://"+s.getHost()+":"+s.getPort():
                    "http://"+s.getHost()+":"+s.getPort();

            String url = urlRoot+"/receive/remote/event";
            Map<String,Object> data = new HashMap<>();
            data.put("sender",currentAppName);
            data.put("value",event.getSource());
            data.put("type",RemoteAppEvent.class.getName());
            String responseContent = restTemplate.postForObject(url,data,String.class);
        }

    }

    public void onContextRefreshedEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        this.discoveryClient =applicationContext.getBean(DiscoveryClient.class);
        this.currentAppName = applicationContext.getEnvironment().getProperty("spring.application.name");

    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return RemoteAppEvent.class.isAssignableFrom(eventType)
                || ContextRefreshedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(@Nullable Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof RemoteAppEvent){
            onApplicationEvent((RemoteAppEvent)event);
        }else if(event instanceof ContextRefreshedEvent){
            onContextRefreshedEvent((ContextRefreshedEvent)event);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
