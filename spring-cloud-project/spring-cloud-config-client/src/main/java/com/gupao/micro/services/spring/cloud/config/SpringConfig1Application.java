package com.gupao.micro.services.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringConfig1Application {


    public static void main(String[] args) {
        SpringApplication.run(SpringConfig1Application.class,args);

//        SpringApplication application = new SpringApplication(SpringConfig1Application.class);
////        application.setWebEnvironment(true);
//        application.run(args);
    }



//    @Configuration
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public static class myPropertySourceLocator implements PropertySourceLocator {
//
//
//        @Override
//        public PropertySource<?> locate(Environment environment) {
//            Map<String, Object> source = new HashMap<>();
//            source.put("server.port","9090");
//            MapPropertySource propertySource =
//                    new MapPropertySource("my-property-source", source);
//            return propertySource;
//        }
//    }
}
