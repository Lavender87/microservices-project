package com.gupao.springkafkademo.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaCustomerDemo extends Thread{

    private KafkaConsumer<Integer,String> kafkaConsumer;
    private static  Properties properties = new Properties();

    static {
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaCustomerDemo");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);//设置是否为自动提交
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public KafkaCustomerDemo(String topic) {
        this.kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singleton(topic));
    }

    @Override
    public void run() {
        while(true){
            ConsumerRecords<Integer,String> consumerRecords=kafkaConsumer.poll(1000);
            for(ConsumerRecord record:consumerRecords){
                System.out.println("message receiced :"+record.value());
            }
        }
    }

    public static void main(String[] args) {

//        new KafkaCustomerDemo("test").start();
    }
}
