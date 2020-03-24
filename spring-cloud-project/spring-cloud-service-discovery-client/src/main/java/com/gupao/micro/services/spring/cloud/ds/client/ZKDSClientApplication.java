package com.gupao.micro.services.spring.cloud.ds.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZKDSClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZKDSClientApplication.class,args);
    }
}
