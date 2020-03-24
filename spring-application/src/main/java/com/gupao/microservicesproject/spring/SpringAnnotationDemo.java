package com.gupao.microservicesproject.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDemo.class);
        context.refresh();
        System.out.println(context.getBean(SpringAnnotationDemo.class));
    }
}
