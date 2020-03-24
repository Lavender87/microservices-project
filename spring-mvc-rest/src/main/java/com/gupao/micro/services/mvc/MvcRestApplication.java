package com.gupao.micro.services.mvc;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//@EnableAutoConfiguration
@SpringBootApplication
public class MvcRestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MvcRestApplication.class)
        .run(args);
    }
}
