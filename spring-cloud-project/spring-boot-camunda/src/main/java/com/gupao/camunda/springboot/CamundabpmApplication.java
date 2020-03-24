package com.gupao.camunda.springboot;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundabpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundabpmApplication.class, args);
        ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    }
}
