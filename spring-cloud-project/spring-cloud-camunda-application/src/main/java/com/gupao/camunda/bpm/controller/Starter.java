package com.gupao.camunda.bpm.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class Starter implements InitializingBean {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        runtimeService.startProcessInstanceByKey("loanApproval");
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
}
