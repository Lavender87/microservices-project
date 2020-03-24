package com.gupao.edu.vip.ebus;

import com.gupao.edu.vip.ebus.listener.HttpRemoteAppEventListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 *
 * 启动eureka
 * 起送事件接受者
 * 启动本服务，事件创建者
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
public class SpringEventBusApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringEventBusApplication.class)
                .listeners(new HttpRemoteAppEventListener())
                .run(args);
    }
}
