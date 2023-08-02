package com.aurora.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    @Bean
    @LoadBalanced   //负载均衡器，不加负载均衡器，无法在nacos注册中心使用
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate =builder.build();
        return restTemplate;
    }
}