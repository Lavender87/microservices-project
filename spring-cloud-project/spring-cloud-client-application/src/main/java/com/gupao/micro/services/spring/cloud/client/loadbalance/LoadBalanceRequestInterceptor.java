package com.gupao.micro.services.spring.cloud.client.loadbalance;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

public class LoadBalanceRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private DiscoveryClient discoveryClient;

    private volatile Map<String,Set<String>> targetUrlCaches = new HashMap<>();

//    @Value("${spring.application.name}")
//    private String currentServiceName;

    @Scheduled(fixedRate = 10*1000) //10秒钟更新一次
    public synchronized void updateTargetUrils(){
        Map<String, Set<String>> oldTargetUrlCaches = this.targetUrlCaches;

        Map<String,Set<String>> newTargetUrlCaches = new HashMap<>();

        discoveryClient.getServices().forEach(serviceName->{
            List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
            //http://{$host}/{$port}
            Set<String> newTargetUrls = serviceInstanceList.stream()
                    .map(s-> s.isSecure() ? "https://"+s.getHost()+":"+s.getPort():"http://"+s.getHost()+":"+s.getPort()
                    ).collect(Collectors.toSet());
            newTargetUrlCaches.put(serviceName,newTargetUrls);
        });

        this.targetUrlCaches = newTargetUrlCaches;


    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //URI /${appName}/uri
        URI requestUri = request.getURI();
        String path = requestUri.getPath();
        System.out.println("path------"+path);
        String[] parts = StringUtils.split(path.substring(1),"/");
        String appName = parts[0];
        String uri =parts[1];

        List<String> targetUrls = new ArrayList<>(this.targetUrlCaches.get(appName)); //copyOnRight
        //size=3 index 0到2
        int index = new Random().nextInt(targetUrls.size());
        String targetUrl = targetUrls.get(index);
        String actualUrl =targetUrl+"/"+uri+"?"+requestUri.getQuery();

//        //参数格式转化
//        List<HttpMessageConverter<?>> messageConverters = Arrays.asList(new MappingJackson2HttpMessageConverter()
//        ,new StringHttpMessageConverter());
//
//        RestTemplate restTemplate=new RestTemplate(messageConverters);
//        ResponseEntity responseEntity =  restTemplate.getForEntity(actualUrl,InputStream.class);
//        return new SampleClientHttpResponse(responseEntity.getHeaders(),(InputStream)responseEntity.getBody());

        URL url = new URL(actualUrl);
        URLConnection urlConnection=url.openConnection();

        HttpHeaders headers = new HttpHeaders();
        InputStream responseBody = urlConnection.getInputStream();

        return new SampleClientHttpResponse(headers,responseBody);
    }

    private class SampleClientHttpResponse implements ClientHttpResponse{

        private HttpHeaders headers;

        private InputStream body;

        public SampleClientHttpResponse(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 0;
        }

        @Override
        public String getStatusText() throws IOException {
            return "";
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
