package com.gupao.camunda.bpm.controller;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class IdentityController {

    @Autowired
    private IdentityService identityService;

    public void getIdentifyUser(){
        User demoUser = identityService
                .createUserQuery()
                .userId("demo")
                .singleResult();
    }
}
