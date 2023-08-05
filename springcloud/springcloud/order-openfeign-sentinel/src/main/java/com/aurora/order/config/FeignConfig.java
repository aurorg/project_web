package com.aurora.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//全局配置：当使用@Configuration 会将配置作用所有的服务提供方
//局部配置：1:通过配置类：如果只想针对某一个服务进行配置，就不需要加@Configuration
//        2：通过配置文件：
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
