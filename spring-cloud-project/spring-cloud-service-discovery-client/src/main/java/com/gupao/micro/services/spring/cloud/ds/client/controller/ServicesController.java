package com.gupao.micro.services.spring.cloud.ds.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServicesController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public List<String> findAll(){
        return discoveryClient.getServices();
    }

    @GetMapping("/services/instance/{serviceName}")
    public List<String> findServicesInstance(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
                .stream()
                .map(s-> s.getServiceId()+"-"+s.getHost()+":"+s.getPort()
                ).collect(Collectors.toList());
    }

}
