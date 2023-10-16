package com.nageoffer.hippo4j.hippo4jconfignacosdemo;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageCoumerThreadPoolExecutor() {
        String threadPoolId = "message-consume";
        return ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .corePoolSize(100)
                .maximumPoolSize(200)
                .dynamicPool()
                .build();
    }

    @Bean
    @DynamicThreadPool
    public ThreadPoolTaskExecutor messageProduce() {
        return new ThreadPoolTaskExecutor();
    }

    public static void main(String[] args) {
        String threadPoolId = "message-consume";
        ThreadPoolExecutor build = ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .corePoolSize(100)
                .maximumPoolSize(120)
                .build();
    }
}
