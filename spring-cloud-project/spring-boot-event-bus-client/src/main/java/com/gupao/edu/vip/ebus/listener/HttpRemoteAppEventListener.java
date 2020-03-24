package com.gupao.edu.vip.ebus.listener;

import com.gupao.edu.vip.ebus.entity.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationListener;
import org.springframework.web.client.RestTemplate;

import javax.naming.ldap.HasControls;
import java.util.HashMap;
import java.util.Map;

public class HttpRemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void onApplicationEvent(RemoteAppEvent event) {
        Object source = event.getSource();
        for(ServiceInstance s:event.getServiceInstances()){
            String urlRoot =s.isSecure()?
                    "https://"+s.getHost()+":"+s.getPort():
                    "http://"+s.getHost()+":"+s.getPort();

            String url = urlRoot+"/receive/remote/event";
            Map<String,Object> data = new HashMap<>();
            data.put("sender",event.getSender());
            data.put("value",event.getSource());
            data.put("type",RemoteAppEvent.class.getName());
            String responseContent = restTemplate.postForObject(url,data,String.class);
        }

    }
}
