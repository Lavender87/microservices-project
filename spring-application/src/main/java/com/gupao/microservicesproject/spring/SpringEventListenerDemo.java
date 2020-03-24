package com.gupao.microservicesproject.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

public class SpringEventListenerDemo {


    //PayloadApplicationEvent
    public static void main(String[] args){
        //PayloadApplicationEvent
        GenericApplicationContext context = new GenericApplicationContext();
//        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
//            @Override
//            public void onApplicationEvent(ApplicationEvent event) {
//                System.out.println("监听事件："+event);
//            }
//        });

        context.addApplicationListener(new ClosedListener());

        //启动上下文
        context.refresh();
        //ContextRefreshedEvent
        context.publishEvent("HelloWorld");
        //ContextRefreshedEvent
        context.publishEvent(new MyEvent("HelloWorld2020"));
        //ContextClosedEvent
        context.close();

    }

    private static class ClosedListener implements ApplicationListener<ContextClosedEvent>{

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            System.out.println("关闭上下文监听"+event);
        }
    }

    private static class MyEvent extends ApplicationEvent{

        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyEvent(Object source) {
            super(source);
        }
    }
}


/**
 * spring监听事件：ApplicationListener   消息消费者
 * spring事件：ApplicationEvent  消息内容
 * spring广播事件 ApplicationEventMulticaster 消息生产者
 */
