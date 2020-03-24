package com.gupao.springkafkademo;

import com.gupao.springkafkademo.demo.KafkaCustomerDemo;
import com.gupao.springkafkademo.demo.KafkaProduceDemo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.gupao.springkafkademo.mapper")
@SpringBootApplication
public class SpringKafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaDemoApplication.class, args);


	}

}
