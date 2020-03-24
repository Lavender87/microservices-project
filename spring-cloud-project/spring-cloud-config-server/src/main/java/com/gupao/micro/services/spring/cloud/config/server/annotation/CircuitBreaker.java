package com.gupao.micro.services.spring.cloud.config.server.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CircuitBreaker {

    /**
     * 超时时间
     * @return
     */
    long timeout() default 1;
}
