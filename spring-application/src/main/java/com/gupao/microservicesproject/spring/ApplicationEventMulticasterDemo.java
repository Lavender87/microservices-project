package com.gupao.microservicesproject.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

public class ApplicationEventMulticasterDemo {

    public static void main(String[] args) {

        ApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        applicationEventMulticaster.addApplicationListener(event->{
                if(event instanceof  PayloadApplicationEvent){
                    System.out.println("监听事件PayloadApplicationEvent："
                            +PayloadApplicationEvent.class.cast(event).getPayload());
                }else{
                    System.out.println("监听事件："+event);
                }
            }
        );
        applicationEventMulticaster.multicastEvent(new PayloadApplicationEvent<Object>("1","hello"));
        applicationEventMulticaster.multicastEvent(new PayloadApplicationEvent<Object>("2","hello"));


    }
}
