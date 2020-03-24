package com.gupao.edu.vip.ebus;

import com.gupao.edu.vip.ebus.listener.HttpRemoteAppEventListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
public class SpringEventBusOptimApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringEventBusOptimApplication.class)
                .listeners(new HttpRemoteAppEventListener())
                .run(args);
    }
}
