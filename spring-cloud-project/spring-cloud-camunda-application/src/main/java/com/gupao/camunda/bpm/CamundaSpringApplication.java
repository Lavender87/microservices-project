package com.gupao.camunda.bpm;


import com.gupao.camunda.bpm.delegate.CalculateInterestService;
import com.gupao.camunda.bpm.controller.Starter;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.PostConstruct;

//@ImportResource("classpath:spring-config-center.xml")
//@EnableProcessApplication
@SpringBootApplication
public class CamundaSpringApplication {

    @Autowired
    private RuntimeService runtimeService;

    public static void main(String[] args) {
        // SpringApplication.run(CamundaSpringApplication.class,args);
        SpringApplication springApplication =new SpringApplication(CamundaSpringApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

}
