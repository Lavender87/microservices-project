package com.gupao.miscro.service.feign.self;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestClient(name="spring-cloud-server-application")
public interface SayingRestService {

    @GetMapping("/say")
    String getMessage(@RequestParam String Message);
}
