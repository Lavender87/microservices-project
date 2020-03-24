package com.gupao.micro.services.spring.cloud.config.server.aop;

import com.gupao.micro.services.spring.cloud.config.server.annotation.CircuitBreaker;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.*;

@Aspect
@Component
public class ServerControllerAspect {
    ExecutorService executorService= Executors.newFixedThreadPool(20);

    @Around("execution(* com.gupao.micro.services.spring.cloud.config.server" +
            ".controller.ServerController.advancedSay(..)) && args(message)")
    public Object advancedSayTimeOut(ProceedingJoinPoint point,String message) throws Throwable {


        Future<Object> future =executorService.submit(()->{
            Object returnValue =null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable throwable) {
//                throwable.printStackTrace();
                return errConment();
            }

            return returnValue;
        });

        Object returnValue =null;
        try{
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        }catch (TimeoutException e){
//            future.cancel(true);
            return errConment();
//            throw e;
        }

        return returnValue;
    }



    @Around("execution(* com.gupao.micro.services.spring.cloud.config.server" +
            ".controller.ServerController.advancedSay2(..)) && args(message) && @annotation(circuitBreaker)")
    public Object advancedSay2TimeOut(ProceedingJoinPoint point, String message, CircuitBreaker circuitBreaker) throws Throwable {

        long timeout = circuitBreaker.timeout();

        Future<Object> future =executorService.submit(()->{
            Object returnValue =null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable throwable) {
//                throwable.printStackTrace();
                return errConment();
            }

            return returnValue;
        });

        Object returnValue =null;
        try{
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        }catch (TimeoutException e){
//            future.cancel(true);
            return errConment();
//            throw e;
        }

        return returnValue;
    }

    public String errConment(){
        return "fault";
    }

    @PreDestroy
    public void destory(){
        executorService.shutdown();
    }

}
