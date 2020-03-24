package com.gupao.micro.services.spring.cloud.client.controller;

import com.gupao.micro.services.spring.cloud.client.annotation.CustomerLoadBalanced;
import com.gupao.micro.services.spring.cloud.client.loadbalance.LoadBalanceRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    @Autowired
    @CustomerLoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanceTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

//    private volatile Set<String> targetUrls = new HashSet<String>();

//    private volatile Map<String,Set<String>> targetUrlCaches = new HashMap<>();

//    @Value("${spring.application.name}")
//    private String currentServiceName;


//    @Scheduled(fixedRate = 10*1000) //10秒钟更新一次
//    public synchronized void updateTargetUrils(){
//        Map<String,Set<String>> oldTargetUrlCaches = this.targetUrlCaches;
//
//        Map<String,Set<String>> newTargetUrlCaches = new HashMap<>();
//
//        discoveryClient.getServices().forEach(serviceName->{
//            List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
//            //http://{$host}/{$port}
//            Set<String> newTargetUrls = serviceInstanceList.stream()
//                    .map(s-> s.isSecure() ? "https://"+s.getHost()+":"+s.getPort():"http://"+s.getHost()+":"+s.getPort()
//                    ).collect(Collectors.toSet());
//            newTargetUrlCaches.put(serviceName,newTargetUrls);
//        });
//
//        this.targetUrlCaches = newTargetUrlCaches;
//
//
//    }


//    @Scheduled(fixedRate = 10*1000) //10秒钟更新一次
//    public synchronized void updateTargetUrils(){
//        Set<String> oldTargetUrls = targetUrls;
//        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(currentServiceName);
//        //http://{$host}/{$port}
//        Set<String> newTargetUrls = serviceInstanceList.stream()
//                .map(s-> s.isSecure() ? "https://"+s.getHost()+":"+s.getPort():"http://"+s.getHost()+":"+s.getPort()
//                ).collect(Collectors.toSet());
//        this.targetUrls =newTargetUrls;
//        oldTargetUrls.clear();
//
//    }

//    @GetMapping("/invoke/say")
//    public String invokeSay(@RequestParam String message){
//        //获取服务器列表
//        //轮训列表
//        //选择其中一台发送
//        //RestTemplate发送请求到服务端
//        //输出相应
//        //快照
//        List<String> targetUrls = new ArrayList<>(this.targetUrls); //copyOnRight
//        //size=3 index 0到2
//        int index = new Random().nextInt(targetUrls.size());
//        String targetUrl = targetUrls.get(index);
//        return restTemplate.getForObject(targetUrl+"/say?message="+message,String.class);
//    }


    @GetMapping("/invoke/{serviceName}/say")
    public String invokeSay(@PathVariable String serviceName, @RequestParam String message){
        //获取服务器列表
        //轮训列表
        //选择其中一台发送
        //RestTemplate发送请求到服务端
        //输出相应
        //快照
//        List<String> targetUrls = new ArrayList<>(this.targetUrlCaches.get(serviceName)); //copyOnRight
//        //size=3 index 0到2
//        int index = new Random().nextInt(targetUrls.size());
//        String targetUrl = targetUrls.get(index);
        return restTemplate.getForObject("/"+serviceName+"/say?message="+message,String.class);
    }

    @GetMapping("/lb/invoke/{serviceName}/say")
    public String lbalanceInvokeSay(@PathVariable String serviceName, @RequestParam String message){

        return loadBalanceTemplate.getForObject("http://"+serviceName+"/say?message="+message,String.class);
    }



    @Bean
    public ClientHttpRequestInterceptor interceptor(){
        return new LoadBalanceRequestInterceptor();
    }

    @LoadBalanced
    @Bean
    public RestTemplate loadBalanceTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor){
//        RestTemplate restTemplate= new RestTemplate();
//        restTemplate.setInterceptors(Arrays.asList(interceptor));
//        return restTemplate;
//    }

    @Bean
    @Autowired
    @CustomerLoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    @Autowired
    public Object customizer(@CustomerLoadBalanced Collection<RestTemplate>restTemplates
            ,ClientHttpRequestInterceptor interceptor){
        restTemplates.forEach(r->{
            r.setInterceptors(Arrays.asList(interceptor));
        });

        return new Object();

    }

}
