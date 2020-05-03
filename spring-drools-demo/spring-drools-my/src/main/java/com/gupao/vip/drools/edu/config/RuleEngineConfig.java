package com.gupao.vip.drools.edu.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;


@Configuration
public class RuleEngineConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngineConfig.class) ;
    private static final String RULES_PATH = "droolRule/";
    private final KieServices kieServices = KieServices.Factory.get();


    @Bean
    public KieContainer kieContainer() throws IOException {
        return KieServices.Factory.get().getKieClasspathContainer();
    }
//    @Bean
//    public KieBase kieBase() throws IOException {
//        return kieContainer().getKieBase();
//    }
    @Bean
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession( "ksession-hello"); //"ksession-hello"
    }


}
