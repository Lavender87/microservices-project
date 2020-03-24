package com.gupao.micro.services.spring.cloud.config.server.controller;

import com.gupao.micro.services.spring.cloud.config.server.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ServerController {

    @GetMapping("/advanced/say")
    public String advancedSay(@RequestParam String message)throws Exception{

        return doSay2(message);
    }


    @GetMapping("/advanced/say2")
    @CircuitBreaker(timeout=100)
    public String advancedSay2(@RequestParam String message)throws Exception{

        return doSay2(message);
    }


    private String doSay2(String message) throws Exception {
        int value =new Random().nextInt(200);
        System.out.println("doSay2 costs "+value+" ms ");
        Thread.sleep(value);

        String returnValue = " say2 "+message;
        System.out.println(returnValue);
        return returnValue;
    }
}
