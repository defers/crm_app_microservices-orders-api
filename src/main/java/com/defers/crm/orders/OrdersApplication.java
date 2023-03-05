package com.defers.crm.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// TODO optimistic, pessimistic block
// TODO kafka, feign, soap ws
// TODO swagger
@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

}
