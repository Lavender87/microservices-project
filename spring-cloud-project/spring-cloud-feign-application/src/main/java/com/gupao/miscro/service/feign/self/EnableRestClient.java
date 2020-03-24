package com.gupao.miscro.service.feign.self;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
//@Documented
//@Import(RestClientsRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import()
public @interface EnableRestClient {
}
