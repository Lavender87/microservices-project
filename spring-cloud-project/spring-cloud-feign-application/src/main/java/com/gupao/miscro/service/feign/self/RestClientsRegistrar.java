package com.gupao.miscro.service.feign.self;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

public class RestClientsRegistrar implements ImportBeanDefinitionRegistrar ,
        BeanFactoryAware, EnvironmentAware {

    private BeanFactory beanFactory;

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        ClassLoader classLoader = importingClassMetadata.getClass().getClassLoader();
        Map<String,Object> attributes =
                importingClassMetadata.getAnnotationAttributes(EnableRestClient.class.getName());
        Class<?>[] clientClass = (Class<?>[])attributes.get("clients");
        Stream.of(clientClass)
                .filter(Class::isInterface)
                .filter(interfaceClass->
                    findAnnotation(interfaceClass,RestClient.class)!=null
                )
                .forEach(restClientClass->{
//                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restClientClass}, new InvocationHandler() {
//                        @Override
//                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                            return null;
//                        }
//                    });

                    RestClient restClient = findAnnotation(restClientClass, RestClient.class);
                    // 获取 应用名称（处理占位符）
                    String serviceName = environment.resolvePlaceholders(restClient.name());
                    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{restClientClass}, new RequestMappingMethodInvocationHandler(serviceName,beanFactory));
                    // 实现方略yi ：
//                    registerBeanByFactoryBean(serviceName,proxy,restClientClass,registry);

                    // 实现方略二：SingletonBeanRegistry
                    String beanName = "RestClient." + serviceName;
                    if(registry instanceof SingletonBeanRegistry ){
                        SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry) registry;
                        singletonBeanRegistry.registerSingleton(beanName, proxy);
                    }

                });

    }

    public static void registerBeanByFactoryBean(String serviceName,Object proxy,Class<?> restClientClass,BeanDefinitionRegistry register){
        String beanName = "RestClient." + serviceName;

    }

    private static void registerBeanByFactoryBean1(String serviceName,
                                                  Object proxy, Class<?> restClientClass, BeanDefinitionRegistry registry) {

        String beanName = "RestClient." + serviceName;

        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);

        /**
         *  <bean class="User">
         *          <constructor-arg>${}</construtor-arg>
         *      </bean>
         */
        // 增加第一个构造器参数引用 : proxy
        beanDefinitionBuilder.addConstructorArgValue(proxy);
        // 增加第二个构造器参数引用 : restClientClass
        beanDefinitionBuilder.addConstructorArgValue(restClientClass);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);

    }

    private static class RestClientClassFactoryBean implements FactoryBean{

        private final Object proxy;

        private final Class<?> restClientClass;

        public RestClientClassFactoryBean(Object proxy, Class<?> restClientClass) {
            this.proxy = proxy;
            this.restClientClass = restClientClass;
        }

        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }
}
