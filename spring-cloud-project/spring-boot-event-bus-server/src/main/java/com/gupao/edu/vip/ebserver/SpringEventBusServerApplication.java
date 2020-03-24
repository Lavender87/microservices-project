package com.gupao.edu.vip.ebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringEventBusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventBusServerApplication.class,args);
    }
}