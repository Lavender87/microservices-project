package com.gupao.springkafkademo.demo;

import org.apache.kafka.clients.producer.*;
import org.springframework.kafka.core.KafkaTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProduceDemo extends Thread{

    private final KafkaProducer<Integer,String> kafkaProducer;

    private final String topic;

    private boolean isAnsyc;

    public KafkaProduceDemo(String topic,boolean isAnsyc) {
        Properties properties= new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"KafkaProduceDemo");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<Integer,String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int num = 0;
       System.out.println("begin send message-----------------------"+
               new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        while(num<50){
            String msg =" 来自product的数据 message_"+num;

            if(this.isAnsyc){
                kafkaProducer.send(new ProducerRecord<Integer,String>(topic,msg), new Callback(){

                    @Override
                    public void onCompletion(final RecordMetadata metadata, Exception exception) {
                        if(metadata!=null){
                            System.out.println("metadata.offset = " + metadata.offset()
                                    +" metadata.topicPartition="+metadata.partition()
                                    + ", exception = " + exception);
                        }

                    }
                });
            }else{
                try {
                    RecordMetadata metadata = kafkaProducer.send(new ProducerRecord<Integer,String>(topic,msg)).get();
                    System.out.println("metadata.offset = " + metadata.offset()
                            +" metadata.topicPartition="+metadata.partition());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            num ++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        new KafkaProduceDemo("test",false).start();
    }
}
