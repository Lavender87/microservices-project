package com.gupao.microservicesproject.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAutoConfiguration
public class SpringBootEventDemo {

    public static void main(String[] args) {

        new SpringApplicationBuilder(SpringBootEventDemo.class)
                .listeners(event->{
                    System.err.println("监听事件："+event.getClass().getName());
                })
                .run(args);
    }
}
