package com.gupao.micro.services.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
//@SpringBootApplication
public class SpringBootApplicationBoostrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationBoostrap.class,args);
    }
}
